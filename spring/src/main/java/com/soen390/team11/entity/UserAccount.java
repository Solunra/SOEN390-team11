package com.soen390.team11.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="user_account")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;
    @Column
    private String username;
    @Column
    private String password;

    public UserAccount(Long userID, String username, String password) {
        this.userID = userID;
        this.username = username;
        this.password = password;
    }
    public UserAccount(String username, String password) {
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
