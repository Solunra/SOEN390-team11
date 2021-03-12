package com.soen390.team11.dto;

import com.soen390.team11.constant.Status;

public class CheckStatusResponseDto {
    private String name;
    private Status status;

    public CheckStatusResponseDto(String name, Status status) {
        this.name = name;
        this.status = status;
    }

    public CheckStatusResponseDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
