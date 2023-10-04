package com.moat.task.exception;

public class TokenIsExpiredException extends RuntimeException {
    public TokenIsExpiredException(String message) {
        super(message);
    }
}
