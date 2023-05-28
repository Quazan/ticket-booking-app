package com.quaz.ticket.validation;

import com.quaz.ticket.dto.ScreeningRequestParameters;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ToMoreRecentThenFromValidator implements ConstraintValidator<ToMoreRecentThenFrom, ScreeningRequestParameters> {

    @Override
    public boolean isValid(ScreeningRequestParameters value, ConstraintValidatorContext context) {
        if (value.from().isAfter(value.to())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(String.format("From (%s) is after to (%s), which is invalid.", value.from(), value.to()))
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
