package com.soen390.team11.dto;

import com.soen390.team11.entity.UserAccount;

public class UserRequestDto {

    private String username;
    private String password;
//    private String email;

    public UserRequestDto(String username, String password, String email) {
        this.username = username;
        this.password = password;
//        this.email = email;
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

//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
    public UserAccount getUserAccount() {
        return new UserAccount(username,password);
//        return new UserEntity(username,email,bCryptPasswordEncoder.encode(password));
    }

    @Override
    public String toString() {
        return "UserRequestModel{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
