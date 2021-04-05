package com.soen390.team11.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.OffsetDateTime;

/**
 * data entity of customer information
 */
@Entity(name = "payment")
public class Payment {

    @Id
    @GenericGenerator(name = "id", strategy = "com.soen390.team11.generator.PaymentIDGenerator")
    @GeneratedValue(generator = "id")
    private String payId;
    @Column
    private String type;
    @Column
    private String cardName;
    @Column
    private String cardNum;
    @Column
    private String expireDate;
    @Column
    private String cvc;
    @Column
    private String userID;

    public Payment() {
    }

    public Payment(String type, String cardName, String cardNum, String expireDate, String cvc, String userID) {
        this.type = type;
        this.cardName = cardName;
        this.cardNum = cardNum;
        this.expireDate = expireDate;
        this.cvc = cvc;
        this.userID = userID;
    }

    public String getPayId() {
        return payId;
    }

    public String getType() {
        return type;
    }

    public String getCardName() {
        return cardName;
    }

    public String getCardNum() {
        return cardNum;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public String getCvc() {
        return cvc;
    }

    public String getUserID() {
        return userID;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }
}