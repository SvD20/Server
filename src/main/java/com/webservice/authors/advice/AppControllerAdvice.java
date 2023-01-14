package com.webservice.authors.advice;

import com.webservice.authors.exception.ErrorMessage;
import com.webservice.authors.exception.NoRecordsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;
import java.util.stream.Collectors;

@ControllerAdvice
@Component
public class AppControllerAdvice {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationExceptions(ConstraintViolationException ex){
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        return new ResponseEntity<>(new ErrorMessage(constraintViolations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList()).get(0)), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoRecordsException.class)
    public ResponseEntity<?> handleNoRecordsExceptions(NoRecordsException ex){
        return new ResponseEntity<>(new ErrorMessage(ex.getMessage()), HttpStatus.NOT_FOUND);
    }


}
