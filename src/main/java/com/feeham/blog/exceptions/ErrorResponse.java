package com.feeham.blog.exceptions;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
    private final String exception;
    private final String operation;
    private final String message;
    private final String reason;
    private final HttpStatus status;

    public ErrorResponse(String exception, String operation, String message, String reason, HttpStatus status) {
        this.operation = operation;
        this.message = message;
        this.reason = reason;
        this.status = status;
        this.exception = exception;
    }

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
