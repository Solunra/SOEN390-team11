package com.soen390.team11.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserAccount {

    @Id
    @GeneratedValue
    private Long userID;
    private String username;
    private String password;

    public UserAccount(Long userID, String username, String password) {
        this.userID = userID;
        this.username = username;
        this.password = password;
    }

    protected UserAccount() {
        // Do Nothing
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
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


}
