package com.hawk.demo.validator.demo.validator;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
public class IntegerEnumValidator implements ConstraintValidator<EnumValid, Integer> {
    private EnumValid annotation;

    @Override
    public void initialize(EnumValid constraintAnnotation) {
        this.annotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return !annotation.required();
        }
        try {
            return BaseEnumValidator.isInEnum(annotation, value);
        } catch (Exception e) {
            log.error("EnumValidator catch an exception: ", e);
            return false;
        }
    }
}
