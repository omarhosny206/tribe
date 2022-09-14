package com.example.exception;

public class UserAlreadyFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UserAlreadyFoundException(String message) {
        super(message);
    }
}
