package com.example.message;

public class MessageResponse {
    private int statusCode;
    private String message;

    public MessageResponse( String message) {
        this.message = message;

    }


    public String getMessage() {
        return message;
    }
}