package com.portfolio.ecsite.controller.advice;

import com.portfolio.ecsite.service.item.ItemEntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(ItemEntityNotFoundException.class)
    public ResponseEntity<Void> handleTaskEntityNotFoundException(ItemEntityNotFoundException e){
        return ResponseEntity.notFound().build();
    }
}
