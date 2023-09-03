package com.feeham.blog.exceptions;
public class ResourceNotFoundException extends CustomException {
    public ResourceNotFoundException(String message, String operation, String reason) {
        super("ResourceNotFoundException", message, operation, reason);
    }
}
