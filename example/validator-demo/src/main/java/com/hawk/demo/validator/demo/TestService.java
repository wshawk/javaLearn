package com.hawk.demo.validator.demo;

import com.hawk.demo.validator.demo.model.Param;
import com.hawk.demo.validator.demo.model.R;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import javax.validation.ConstraintViolation;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TestService {
    private final SpringValidatorAdapter validator;

    public R<String> add(Param param) {
        Set<ConstraintViolation<Param>> violations = validator.validate(param, param.getAddValidationGroups());

        if (!violations.isEmpty()) {
            String message = violations.stream().map(ConstraintViolation::getMessageTemplate)
                    .collect(Collectors.joining(";"));
            return R.fail(message);
        }

        return R.success("success");
    }
}
