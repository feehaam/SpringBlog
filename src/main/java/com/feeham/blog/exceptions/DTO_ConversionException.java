package com.feeham.blog.exceptions;

// Custom exception class for DTO conversion exceptions
public class DTO_ConversionException extends CustomException {
    public DTO_ConversionException(String message, String operation, String reason) {
        super("DTO_ConversionException", message, operation, reason);
    }
}