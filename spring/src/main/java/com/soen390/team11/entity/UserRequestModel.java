package com.soen390.team11.entity;

public class UserRequestModel {

    private String userName;
    private String password;
//    private String email;

    public UserRequestModel(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
//        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
    public UserAccount getUserAccount(){
        return new UserAccount(userName,password);
//        return new UserEntity(userName,email,bCryptPasswordEncoder.encode(password));
    }

    @Override
    public String toString() {
        return "UserRequestModel{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
