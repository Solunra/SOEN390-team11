package com.soen390.team11.dto;

/**
 * Sign up Request Body
 */
public class UserAccountDto {

    private String username;
    private String password;
    private String email;
    private String role;
    private String userID;

    public UserAccountDto(){

    }

    public UserAccountDto(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email=email;
    }
    public UserAccountDto(String username, String password, String email, String role, String userID) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.userID = userID;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public String getUserID() {
        return userID;
    }

    @Override
    public String toString() {
        return "UserRequestDto{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
