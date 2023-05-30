package com.quaz.ticket.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FromIsBeforeToValidator.class)
public @interface FromIsBeforeTo {

    String message() default "`from` must be before `to`";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
