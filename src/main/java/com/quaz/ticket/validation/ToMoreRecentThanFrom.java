package com.quaz.ticket.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ToMoreRecentThanFromValidator.class)
public @interface ToMoreRecentThanFrom {

    String message() default "`to` should be more recent than `from`";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
