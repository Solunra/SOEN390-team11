package com.soen390.team11.dto;

/**
 * Error message to be sent back to the requesting client
 */
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
