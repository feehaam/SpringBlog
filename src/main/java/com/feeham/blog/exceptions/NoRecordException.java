package com.feeham.blog.exceptions;

// Custom exception class for no record found exceptions
public class NoRecordException extends CustomException {
    public NoRecordException(String message, String operation, String reason) {
        super("NoRecordException", message, operation, reason);
    }
}