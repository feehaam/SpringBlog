package com.feeham.blog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception) {
        return generateResponse(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoRecordException.class)
    public ResponseEntity<?> handleNoRecordException(NoRecordException exception) {
        return generateResponse(exception, HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<?> generateResponse(CustomException exception, HttpStatus status){
        return new ResponseEntity<>(new ErrorResponse(exception.getException(), exception.getMessage(),
                exception.getOperation(), exception.getReason(), status), status);
    }
}
