package com.example.response;

import org.springframework.http.HttpStatus;

import java.util.List;

public class MessageResponse {
    private String message;

    public MessageResponse(String message) {
        this.message = message;

    }
    public String getMessage() {
        return message;
    }
}