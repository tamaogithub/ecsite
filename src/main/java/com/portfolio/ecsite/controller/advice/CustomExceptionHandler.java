package com.portfolio.ecsite.controller.advice;

import com.portfolio.ecsite.model.BadRequestError;
import com.portfolio.ecsite.model.ResourceNotFoundError;
import com.portfolio.ecsite.service.item.ItemEntityNotFoundException;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;


@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ItemEntityNotFoundException.class)
    public ResponseEntity<ResourceNotFoundError> handleTaskEntityNotFoundException(ItemEntityNotFoundException e){
        var error = new ResourceNotFoundError();
        error.setDetail(e.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        var error = BadRequestErrorCreator.from(ex);
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<BadRequestError> handleConstraintViolationException(
            ConstraintViolationException ex
    ) {
        var error = BadRequestErrorCreator.from(ex);
        return ResponseEntity.badRequest().body(error);
    }
}
