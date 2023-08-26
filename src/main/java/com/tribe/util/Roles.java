package com.tribe.util;

public enum Roles {
    AUTHOR("author"),
    READER("reader");

    private final String value;

    Roles(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
