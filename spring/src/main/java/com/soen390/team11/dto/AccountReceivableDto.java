package com.soen390.team11.dto;

import java.time.OffsetDateTime;

/**
 * the infromation of account receive by the customer
 * what the customer buy, how much they pay, when they make purchase
 */
public class AccountReceivableDto {
    private String invoiceDate;
    private String invoiceID;
    private String username;
    private String userid;
    private String amount;

    public AccountReceivableDto(OffsetDateTime invoiceDate, String invoiceNumber, String username,String userid, Double amount) {
        this.invoiceDate = invoiceDate.toLocalDate().toString()+ " At "+invoiceDate.toLocalTime().toString();
        this.invoiceID = invoiceNumber;
        this.username = username;
        this.userid=userid;
        this.amount = "$ "+amount;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public String getInvoiceID() {
        return invoiceID;
    }

    public String getAmount() {
        return amount;
    }

    public String getUsername() {
        return username;
    }

    public String getUserid() {
        return userid;
    }
}
