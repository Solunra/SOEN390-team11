package com.soen390.team11.dto;

public class Error {
    private String message;

    public Error(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "{" +
                "\'message\': \'" + message + '\'' +
                '}';
    }
}
