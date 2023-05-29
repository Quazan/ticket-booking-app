package com.quaz.ticket.validation;

import com.quaz.ticket.dto.TimeRange;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ToMoreRecentThanFromValidator implements ConstraintValidator<ToMoreRecentThanFrom, TimeRange> {

    @Override
    public boolean isValid(TimeRange value, ConstraintValidatorContext context) {
        if (value.from().isAfter(value.to())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(String.format("From (%s) is after to (%s), which is invalid.", value.from(), value.to()))
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
