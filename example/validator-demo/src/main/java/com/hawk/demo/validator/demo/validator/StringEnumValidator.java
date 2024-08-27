package com.hawk.demo.validator.demo.validator;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

@Slf4j
public class StringEnumValidator implements ConstraintValidator<EnumValid, String> {
    private EnumValid annotation;

    @Override
    public void initialize(EnumValid constraintAnnotation) {
        this.annotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (Objects.isNull(value)) {
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
