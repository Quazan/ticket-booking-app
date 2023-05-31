package com.quaz.ticket.mapper;

import com.quaz.ticket.dto.ReservationRequest;
import com.quaz.ticket.dto.ReservationResponse;
import com.quaz.ticket.dto.TicketRequest;
import com.quaz.ticket.entity.Reservation;
import com.quaz.ticket.entity.Screening;
import com.quaz.ticket.entity.ScreeningSeat;
import com.quaz.ticket.entity.Ticket;
import com.quaz.ticket.entity.TicketType;
import com.quaz.ticket.entity.Voucher;
import com.quaz.ticket.repository.ScreeningRepository;
import com.quaz.ticket.repository.ScreeningSeatRepository;
import com.quaz.ticket.repository.TicketTypeRepository;
import com.quaz.ticket.repository.VoucherRepository;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import java.math.BigDecimal;
import java.time.Clock;
import java.util.List;

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

    @Mapping(target = "reservationTime", expression = "java(OffsetDateTime.now(clock))")
    @Mapping(target = "voucher", source = "voucherCode")
    @Mapping(target = "totalPrice", ignore = true)
    @Mapping(target = "screening", source = "screeningId")
    public abstract Reservation toEntity(ReservationRequest reservationRequest);

    @AfterMapping
    void linkTickets(@MappingTarget Reservation reservation) {
        reservation.getTickets().forEach(ticket -> ticket.setReservation(reservation));
    }

    @AfterMapping
    void setTotalPrice(@MappingTarget Reservation reservation) {
        final var price = reservation.getTickets().stream()
                .map(ticket -> ticket.getPrice(reservation.getReservationTime()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        final var totalPrice = reservation.getVoucher()
                .map(voucher -> voucher.apply(price))
                .orElse(price);

        reservation.setTotalPrice(totalPrice);
    }

    Screening mapScreeningId(Long screeningId) {
        return screeningRepository.findById(screeningId).orElseThrow();
    }

    List<ScreeningSeat> mapScreeningSeatIds(List<Long> value) {
        return screeningSeatRepository.findAllById(value);
    }

    Voucher mapVoucherCodeToVoucher(String voucherCode) {
        return voucherRepository.findByCodeLike(voucherCode).stream()
                .findFirst()
                .orElse(null);
    }

    @Mapping(source = "ticketTypeId", target = "ticketType")
    @Mapping(target = "reservation", ignore = true)
    abstract Ticket toTickets(TicketRequest ticketRequests);

    TicketType ticketTypeIdToTicketType(Long id) {
        return ticketTypeRepository.findById(id).orElseThrow();
    }

    public abstract ReservationResponse toResponse(Reservation reservation);

}