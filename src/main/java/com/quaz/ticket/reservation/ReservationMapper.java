package com.quaz.ticket.reservation;

import com.quaz.ticket.screeningseat.ScreeningSeatMapper;
import com.quaz.ticket.screening.Screening;
import com.quaz.ticket.screeningseat.ScreeningSeat;
import com.quaz.ticket.tickettype.TicketType;
import com.quaz.ticket.voucher.Voucher;
import com.quaz.ticket.screening.ScreeningRepository;
import com.quaz.ticket.screeningseat.ScreeningSeatRepository;
import com.quaz.ticket.tickettype.TicketTypeRepository;
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

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {ScreeningSeatMapper.class})
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

    @Mapping(target = "reservationTime", expression = "java(OffsetDateTime.now(clock))")
    @Mapping(target = "voucher", source = "voucherCode")
    @Mapping(target = "totalPrice", ignore = true)
    @Mapping(target = "screening", source = "screeningId")
    @Mapping(target = "reservedSeats", source = "reservedSeatIds")
    public abstract Reservation toEntity(ReservationRequest reservationRequest);

    @AfterMapping
    void linkTickets(@MappingTarget Reservation reservation) {
        reservation.getTickets().forEach(ticket -> ticket.setReservation(reservation));
    }

    @AfterMapping
    void setTotalPrice(@MappingTarget Reservation reservation) {
        final var price = reservation.getTickets().stream()
                .map(ticket -> ticket.getPrice(reservation.getScreening().getScreeningTime()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        final var totalPrice = reservation.getVoucher()
                .map(voucher -> voucher.apply(price))
                .orElse(price);

        reservation.setTotalPrice(totalPrice);
    }

    Screening mapScreeningId(Long screeningId) {
        return screeningRepository.findById(screeningId).orElseThrow();
    }

    List<ScreeningSeat> mapScreeningSeatIds(Set<Long> value) {
        return screeningSeatRepository.findAllById(value);
    }

    Voucher mapVoucherCodeToVoucher(String voucherCode) {
        return voucherRepository.findFirstByCodeLike(voucherCode);
    }

    @Mapping(source = "ticketTypeId", target = "ticketType")
    @Mapping(target = "reservation", ignore = true)
    abstract Ticket toTickets(TicketRequest ticketRequests);

    TicketType ticketTypeIdToTicketType(Long id) {
        return ticketTypeRepository.findById(id).orElseThrow();
    }

    public abstract ReservationResponse toResponse(Reservation reservation);

}