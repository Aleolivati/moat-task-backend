package com.moat.task.exception;

public class UserPasswordIncorrectException extends RuntimeException {
    public UserPasswordIncorrectException(String message) {
        super(message);
    }
}
