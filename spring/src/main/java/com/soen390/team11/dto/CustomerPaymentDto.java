package com.soen390.team11.dto;

import com.soen390.team11.entity.Customer;

import javax.persistence.Column;

public class CustomerPaymentDto {
    private String type;
    private String cardName;
    private Integer cardNum;
    private String expireDate;
    private Integer cvc;
    private String payId;

    public CustomerPaymentDto(String type, String cardName, Integer cardNum, String expireDate, Integer cvc,String payId) {
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

    public Integer getCardNum() {
        return cardNum;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public Integer getCvc() {
        return cvc;
    }

    public String getPayId() {
        return payId;
    }
}
