package com.soen390.team11.entity;

import java.util.Set;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Database Entity for User Account
 */
@Entity(name="user_account")
public class UserAccount {

    @Id
    @GenericGenerator(name="userID", strategy = "com.soen390.team11.generator.UserIDGenerator")
    @GeneratedValue(generator="userID")
    private String userID;
    @Column
    private String username;
    @Column
    private String password;
    @Column(unique = true)
    private String email;
    @Column
    private String role;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name="userID")
    private Set<Customer> customers;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name="userID")
    private Set<Payment> payments;

    public UserAccount(String username, String password, String email, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    protected UserAccount() {
        // Do Nothing
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
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

    public void setRole(String role) {
        this.role = role;
    }
    @Override
    public String toString() {
        return "UserAccount{" +
                "userID=" + userID +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Set<Customer> getCustomers() {
        return customers;
    }
    public Set<Payment> getPayments() {
        return payments;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }
}
