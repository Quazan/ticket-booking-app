package com.quaz.ticket.mapper;

import com.quaz.ticket.dto.ReservationRequest;
import com.quaz.ticket.dto.ReservationResponse;
import com.quaz.ticket.dto.TicketDto;
import com.quaz.ticket.entity.Reservation;
import com.quaz.ticket.entity.ScreeningSeat;
import com.quaz.ticket.entity.Ticket;
import com.quaz.ticket.entity.TicketType;
import com.quaz.ticket.entity.Voucher;
import com.quaz.ticket.repository.ScreeningSeatRepository;
import com.quaz.ticket.repository.TicketTypeRepository;
import com.quaz.ticket.repository.VoucherRepository;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class ReservationMapper {

    @Autowired
    private ScreeningSeatRepository screeningSeatRepository;

    @Autowired
    private VoucherRepository voucherRepository;

    @Autowired
    private TicketTypeRepository ticketTypeRepository;

    @Mapping(target = "reservationTime", ignore = true)
    @Mapping(target = "voucher", source = "voucherCode")
    public abstract Reservation toEntity(ReservationRequest reservationRequest);

    @AfterMapping
    void linkTickets(@MappingTarget Reservation reservation) {
        reservation.getTickets().forEach(ticket -> ticket.setReservation(reservation));
    }

    List<Long> reservedSeatsToReservedSeatIds(List<ScreeningSeat> reservedSeats) {
        return reservedSeats.stream().map(ScreeningSeat::getId).toList();
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
    abstract Ticket toTicket(TicketDto ticketDto);

    TicketType ticketTypeIdToTicketType(Long id) {
        return ticketTypeRepository.findById(id).orElseThrow();
    }

    public abstract ReservationResponse toResponse(Reservation reservation);

}