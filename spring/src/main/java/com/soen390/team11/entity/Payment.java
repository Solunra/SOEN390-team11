package com.soen390.team11.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.OffsetDateTime;

/**
 * data entity of customer information
 */
@Entity
public class Payment {

    @Column
    private String type;
    @Column
    private String cardName;
    @Id
    private Integer cardNum;
    @Column
    private OffsetDateTime expireDate;
    @Column
    private Integer cvc;

    public Payment() {
        //Empty constructor.
    }

    public Payment(String type, String cardName, Integer cardNum, OffsetDateTime expireDate, Integer cvc) {
        this.type = type;
        this.cardName = cardName;
        this.cardNum = cardNum;
        this.expireDate = expireDate;
        this.cvc = cvc;
    }


    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }


    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public Integer getCardNum() {
        return cardNum;
    }

    public void setCardNum(Integer cardNum) {
        this.cardNum = cardNum;
    }

    public OffsetDateTime getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(OffsetDateTime expireDate) {
        this.expireDate = expireDate;
    }

    public Integer getCvc() {
        return cvc;
    }

    public void setCvc(Integer cvc) {
        this.cvc = cvc;
    }
}
