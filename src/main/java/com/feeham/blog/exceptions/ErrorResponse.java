package com.feeham.blog.exceptions;

import org.springframework.http.HttpStatus;

// This class represents an error response object that is used to handle exceptions in the application.
public class ErrorResponse {
    private final String exception;   // The type of exception that occurred.
    private final String operation;   // The operation or request that triggered the exception.
    private final String message;     // A descriptive message explaining the exception.
    private final String reason;      // A reason or additional information about the exception.
    private final HttpStatus status;  // The HTTP status code associated with the exception.

    // Constructor to initialize the ErrorResponse object with the provided information.
    public ErrorResponse(String exception, String operation, String message, String reason, HttpStatus status) {
        this.operation = operation;
        this.message = message;
        this.reason = reason;
        this.status = status;
        this.exception = exception;
    }

    // Getters and setters
    public String getOperation() {
        return operation;
    }
    public String getMessage() {
        return message;
    }
    public String getReason() {
        return reason;
    }
    public HttpStatus getStatus() {
        return status;
    }
    public String getException() {
        return exception;
    }
}
