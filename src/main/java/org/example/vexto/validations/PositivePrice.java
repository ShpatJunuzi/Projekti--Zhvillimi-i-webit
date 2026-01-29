package org.example.vexto.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PositivePriceValidator.class)
public @interface PositivePrice {
    String message() default "Price has to be larger than 0!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}