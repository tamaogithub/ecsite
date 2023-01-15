package com.portfolio.ecsite.controller.advice;

import com.portfolio.ecsite.model.BadRequestError;
import com.portfolio.ecsite.model.InvalidParam;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ElementKind;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class BadRequestErrorCreator {
    public static BadRequestError from(MethodArgumentNotValidException ex) {
        var invalidParamList = ex.getFieldErrors()
                .stream()
                .map(BadRequestErrorCreator::getInvalidParam)
                .collect(Collectors.toList());

        var error = new BadRequestError();
        error.setInvalidParams(invalidParamList);
        return error;
    }

    private static InvalidParam getInvalidParam(FieldError fieldError) {
        var invalidParam = new InvalidParam();
        invalidParam.setName(fieldError.getField());
        invalidParam.setReason(fieldError.getDefaultMessage());
        return invalidParam;
    }

    public static BadRequestError from(ConstraintViolationException ex) {
        var invalidParamList = ex.getConstraintViolations()
                .stream()
                .map(BadRequestErrorCreator::createInvalidInvalidParam)
                .collect(Collectors.toList());

        var error = new BadRequestError();
        error.setInvalidParams(invalidParamList);

        return error;
    }

    private static InvalidParam createInvalidInvalidParam(ConstraintViolation<?> violation) {
        var parameterOpt = StreamSupport.stream(violation.getPropertyPath().spliterator(), false)
                .filter(node -> node.getKind().equals(ElementKind.PARAMETER))
                .findFirst();
        var invalidParam = new InvalidParam();
        parameterOpt.ifPresent(p -> invalidParam.setName(p.getName()));
        invalidParam.setReason(violation.getMessage());

        return invalidParam;
    }
}
