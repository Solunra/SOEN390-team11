package com.soen390.team11.dto;

public class UserRequestDto {

    private String username;
    private String password;

    public UserRequestDto(String username, String password, String email) {
        this.username = username;
        this.password = password;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserRequestModel{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
