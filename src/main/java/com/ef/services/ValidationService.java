package com.ef.services;

import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import java.util.Set;

@Service
public class ValidationService {
    @Autowired private Validator validator;

    @Bean
    public Validator validator() {
        Validator validator = Validation.byDefaultProvider()
                .configure()
                .messageInterpolator(new ParameterMessageInterpolator())
                .buildValidatorFactory()
                .getValidator();

        return validator;
    }

    public <T> void validate(T config) throws ValidationException {
        Set<ConstraintViolation<T>> violations = validator.validate(config);

        for (ConstraintViolation<T> violation : violations) {
            throw new ValidationException(violation.getPropertyPath().toString() + ": " + violation.getMessage());
        }
    }
}
