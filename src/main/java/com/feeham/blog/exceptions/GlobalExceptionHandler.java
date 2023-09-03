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

    @ExceptionHandler(DTO_ConversionException.class)
    public ResponseEntity<?> handleDTO_ConversionException(DTO_ConversionException exception) {
        return generateResponse(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> handlerNullPointerException(NullPointerException exception) {
        return new ResponseEntity<>(new ErrorResponse("NullPointerException", "Failed to process null value",
                "Null pointer reference", "Null value could not be referenced", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handlerException(Exception exception) {
        return new ResponseEntity<>(new ErrorResponse("Exception", "Failed to perform operation",
                "Uncaught error", "Unknown", HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<?> generateResponse(CustomException exception, HttpStatus status){
        return new ResponseEntity<>(new ErrorResponse(exception.getException(), exception.getMessage(),
                exception.getOperation(), exception.getReason(), status), status);
    }
}
