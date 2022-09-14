package com.example.response;

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