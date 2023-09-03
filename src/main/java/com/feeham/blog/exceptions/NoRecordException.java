package com.feeham.blog.exceptions;

public class NoRecordException extends CustomException {
    public NoRecordException(String message, String operation, String reason) {
        super("NoRecordException", message, operation, reason);
    }
}

