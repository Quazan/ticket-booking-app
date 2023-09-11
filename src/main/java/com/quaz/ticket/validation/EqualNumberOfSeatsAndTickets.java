package com.quaz.ticket.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EqualNumberOfSeatsAndTicketsValidator.class)
public @interface EqualNumberOfSeatsAndTickets {

    String message() default "number of seats and tickets must be equal";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
