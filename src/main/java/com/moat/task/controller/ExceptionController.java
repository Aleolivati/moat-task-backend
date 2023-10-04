package com.moat.task.controller;

import com.moat.task.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler({TokenNotFoundException.class, TokenNotProvidedException.class, TokenIsExpiredException.class })
    public ResponseEntity<String> handleTokenNotFoundException(Throwable err) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err.getMessage());
    }

    @ExceptionHandler({UserAlreadyLoggedInException.class, UserPasswordIncorrectException.class, UserAlreadyExistsException.class })
    public ResponseEntity<String> handleUserAlreadyLoggedInException(Throwable err) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException err) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err.getMessage());
    }
}
