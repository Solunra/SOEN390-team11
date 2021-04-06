package com.soen390.team11.dto;

import com.soen390.team11.entity.Customer;

import javax.persistence.Column;

/**
 * information of the customer payment
 */
public class CustomerPaymentDto {
    private String type;
    private String cardName;
    private String cardNum;
    private String expireDate;
    private String cvc;
    private String payId;

    public CustomerPaymentDto(String type, String cardName, String cardNum, String expireDate, String cvc,String payId) {
        this.type = type;
        this.cardName = cardName;
        this.cardNum = cardNum;
        this.expireDate = expireDate;
        this.cvc = cvc;
        this.payId = payId;
    }

    public CustomerPaymentDto() {
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

    public String getPayId() {
        return payId;
    }
}
