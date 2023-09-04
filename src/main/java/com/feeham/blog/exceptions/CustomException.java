package com.feeham.blog.exceptions;

// Custom base exception class extending RuntimeException
public class CustomException extends RuntimeException {
    // A unique identifier for the specific exception type
    private final String exception;

    // The operation or action that triggered the exception
    private final String operation;

    // A descriptive message providing more details about the exception
    private final String message;

    // The reason or cause of the exception
    private final String reason;

    public CustomException(String exception, String operation, String message, String reason) {
        this.exception = exception;
        this.operation = operation;
        this.message = message;
        this.reason = reason;
    }

    // Getters and setters for exception
    public String getException() {
        return exception;
    }
    public String getOperation() {
        return operation;
    }
    @Override
    public String getMessage() {
        return message;
    }
    public String getReason() {
        return reason;
    }
}
