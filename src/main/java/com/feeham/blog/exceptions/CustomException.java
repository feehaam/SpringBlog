package com.feeham.blog.exceptions;

public class CustomException extends RuntimeException{
    private final String exception;
    private final String operation;
    private final String message;
    private final String reason;

    public CustomException(String exception, String operation, String message, String reason) {
        this.exception = exception;
        this.operation = operation;
        this.message = message;
        this.reason = reason;
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

    public String getException() {
        return exception;
    }
}
