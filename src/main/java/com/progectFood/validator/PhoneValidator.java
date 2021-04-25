package com.progectFood.validator;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<CustomPhone, String> {

    private CustomPhone customPhone;

    @Override
    public void initialize(CustomPhone constraintAnnotation) {
        this.customPhone = constraintAnnotation;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return StringUtils.isNoneEmpty(value) && value.matches("(\\+79)(\\d{9})");
    }
}