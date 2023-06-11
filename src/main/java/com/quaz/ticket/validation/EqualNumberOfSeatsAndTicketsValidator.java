package com.quaz.ticket.validation;

import com.quaz.ticket.reservation.ReservationRequest;
import com.quaz.ticket.reservation.TicketRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EqualNumberOfSeatsAndTicketsValidator implements ConstraintValidator<EqualNumberOfSeatsAndTickets, ReservationRequest> {

    @Override
    public boolean isValid(ReservationRequest value, ConstraintValidatorContext context) {
        if (value.tickets() == null || value.reservedSeatIds() == null) {
            return false;
        }
        return value.tickets().stream().mapToInt(TicketRequest::count).sum() == value.reservedSeatIds().size();
    }
}
