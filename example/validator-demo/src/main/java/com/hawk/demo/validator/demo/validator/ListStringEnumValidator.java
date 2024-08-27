package com.hawk.demo.validator.demo.validator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * @author WENSONG1
 */
@Slf4j
public class ListStringEnumValidator implements ConstraintValidator<EnumValid, List<String>> {
    private EnumValid annotation;

    @Override
    public void initialize(EnumValid constraintAnnotation) {
        this.annotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(List<String> valueList, ConstraintValidatorContext constraintValidatorContext) {
        if (CollectionUtils.isEmpty(valueList)) {
            return !annotation.required();
        }
        try {
            return BaseEnumValidator.isInEnumByListString(annotation, valueList);
        } catch (Exception e) {
            log.error("EnumValidator catch an exception: ", e);
            return false;
        }
    }

}
