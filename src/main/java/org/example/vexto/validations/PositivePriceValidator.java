package org.example.vexto.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PositivePriceValidator implements ConstraintValidator<PositivePrice, Double> {
    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        if (value == null) return false;
        return value > 0;
    }
}