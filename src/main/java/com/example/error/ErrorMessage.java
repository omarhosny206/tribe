package com.example.error;

import java.util.Date;

public class ErrorMessage {
    private int statusCode;
    private String message;

    public ErrorMessage(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;

    }

    public int getStatusCode() {
        return statusCode;
    }



    public String getMessage() {
        return message;
    }

}