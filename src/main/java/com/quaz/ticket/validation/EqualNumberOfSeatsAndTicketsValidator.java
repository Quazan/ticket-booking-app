package com.quaz.ticket.validation;

import com.quaz.ticket.dto.ReservationRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EqualNumberOfSeatsAndTicketsValidator implements ConstraintValidator<EqualNumberOfSeatsAndTickets, ReservationRequest> {

    @Override
    public boolean isValid(ReservationRequest value, ConstraintValidatorContext context) {
        return value.tickets().size() == value.reservedSeats().size();
    }
}
