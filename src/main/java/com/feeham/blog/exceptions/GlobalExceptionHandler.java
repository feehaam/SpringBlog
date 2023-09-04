package com.feeham.blog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
// This class serves as a global exception handler for handling various types of exceptions in the application.
public class GlobalExceptionHandler {

    // Exception handler for ResourceNotFoundException, handles not found exceptions.
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception) {
        return generateResponse(exception, HttpStatus.NOT_FOUND);
    }

    // Exception handler for NoRecordException, handles exceptions when no records are found.
    @ExceptionHandler(NoRecordException.class)
    public ResponseEntity<?> handleNoRecordException(NoRecordException exception) {
        return generateResponse(exception, HttpStatus.NOT_FOUND);
    }

    // Exception handler for DTO_ConversionException, handles bad request exceptions.
    @ExceptionHandler(DTO_ConversionException.class)
    public ResponseEntity<?> handleDTO_ConversionException(DTO_ConversionException exception) {
        return generateResponse(exception, HttpStatus.BAD_REQUEST);
    }

    // Exception handler for NullPointerException, handles null pointer exceptions.
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> handlerNullPointerException(NullPointerException exception) {
        return new ResponseEntity<>(new ErrorResponse("NullPointerException", "Failed to process null value",
                "Null pointer reference", "Null value could not be referenced", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    // Exception handler for generic Exception, handles uncaught exceptions.
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handlerException(Exception exception) {
        return new ResponseEntity<>(new ErrorResponse("Exception", "Failed to perform operation",
                "Uncaught error", "Unknown", HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Helper method to generate a ResponseEntity with a standardized ErrorResponse.
    private ResponseEntity<?> generateResponse(CustomException exception, HttpStatus status){
        return new ResponseEntity<>(new ErrorResponse(exception.getException(), exception.getMessage(),
                exception.getOperation(), exception.getReason(), status), status);
    }
}
