package com.luv2code.springdemo.exceptionhandler;

import com.luv2code.springdemo.entity.InvalidNumberError;
import com.luv2code.springdemo.exceptionhandler.InvalidCustomerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceController {

    @ExceptionHandler
    public ResponseEntity<InvalidNumberError> handleInvalidException(InvalidCustomerException exc) {
        InvalidNumberError error = new InvalidNumberError();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler
    public ResponseEntity<InvalidNumberError> handleBadRequestException(Exception exc) {

        InvalidNumberError error = new InvalidNumberError();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimestamp(System.currentTimeMillis());


        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }


}