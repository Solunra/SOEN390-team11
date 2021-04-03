package com.soen390.team11.entity;

public class Log {

    private String type;
    private String time;
    private String message;

    public Log(String type, String time, String message) {
        this.type = type;
        this.time = time;
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
