package com.quaz.ticket.reservation;

import com.quaz.ticket.exception.EntityNotFoundException;
import com.quaz.ticket.screening.Screening;
import com.quaz.ticket.screening.ScreeningRepository;
import com.quaz.ticket.screeningseat.ScreeningSeat;
import com.quaz.ticket.screeningseat.ScreeningSeatRepository;
import com.quaz.ticket.tickettype.TicketType;
import com.quaz.ticket.tickettype.TicketTypeRepository;
import com.quaz.ticket.voucher.Voucher;
import com.quaz.ticket.voucher.VoucherRepository;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import java.math.BigDecimal;
import java.time.Clock;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class ReservationMapper {

    @Autowired
    protected Clock clock;

    @Autowired
    private ScreeningSeatRepository screeningSeatRepository;

    @Autowired
    private ScreeningRepository screeningRepository;

    @Autowired
    private VoucherRepository voucherRepository;

    @Autowired
    private TicketTypeRepository ticketTypeRepository;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "reservationTime", expression = "java(OffsetDateTime.now(clock))")
    @Mapping(target = "expirationTime", ignore = true)
    @Mapping(target = "voucher", source = "voucherCode")
    @Mapping(target = "totalPrice", ignore = true)
    @Mapping(target = "screening", source = "screeningId")
    @Mapping(target = "reservedSeats", source = "reservedSeatIds")
    public abstract Reservation toEntity(ReservationRequest reservationRequest);

    @AfterMapping
    protected void setExpirationTime(@MappingTarget Reservation reservation) {
        reservation.setExpirationTime(reservation.getScreening().getScreeningTime());
    }

    @AfterMapping
    protected void linkTickets(@MappingTarget Reservation reservation) {
        reservation.getTickets().forEach(ticket -> ticket.setReservation(reservation));
    }

    @AfterMapping
    protected void setTotalPrice(@MappingTarget Reservation reservation) {
        final var price = reservation.getTickets().stream()
                .map(ticket -> ticket.getPrice(reservation.getScreening().getScreeningTime()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        final var totalPrice = reservation.getVoucher()
                .map(voucher -> voucher.apply(price))
                .orElse(price);

        reservation.setTotalPrice(totalPrice);
    }

    protected Screening mapScreeningId(Long screeningId) {
        return screeningRepository.findById(screeningId)
                .orElseThrow(() -> new EntityNotFoundException(Screening.class, screeningId));
    }

    protected List<ScreeningSeat> mapScreeningSeatIds(Set<Long> value) {
        return screeningSeatRepository.findAllById(value);
    }

    protected Voucher mapVoucherCodeToVoucher(String voucherCode) {
        return voucherRepository.findFirstByCodeLike(voucherCode);
    }

    @Mapping(target = "ticketType", source = "ticketTypeId")
    @Mapping(target = "reservation", ignore = true)
    @Mapping(target = "id", ignore = true)
    protected abstract Ticket toTickets(TicketRequest ticketRequests);

    protected TicketType ticketTypeIdToTicketType(Long id) {
        return ticketTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(TicketType.class, id));
    }

    @Mapping(target = "row", source = "seat.row")
    @Mapping(target = "seatNumber", source = "seat.number")
    protected abstract ScreeningSeatReservationEmbeddedResponse toScreeningSeatReservationEmbeddedResponse(ScreeningSeat screeningSeat);

    public abstract ReservationResponse toResponse(Reservation reservation);

}