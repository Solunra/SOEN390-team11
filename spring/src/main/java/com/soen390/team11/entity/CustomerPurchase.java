package com.soen390.team11.entity;

import com.soen390.team11.constant.Status;

import javax.persistence.*;

/**
 * data entity for customer purchase
 */
@Entity(name = "customer_purchase")
public class CustomerPurchase {
    @EmbeddedId
    CustomerPurchaseId customerPurchaseId;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column
    private Integer amount;
    @Column
    private String userid;

    public CustomerPurchase() {
    }

    public CustomerPurchase(CustomerPurchaseId customerPurchaseId, Status status, Integer amount,String userid) {
        this.customerPurchaseId = customerPurchaseId;
        this.status = status;
        this.amount = amount;
        this.userid =userid;
    }

    public CustomerPurchaseId getCustomerPurchaseId() {
        return customerPurchaseId;
    }

    public void setCustomerPurchaseId(CustomerPurchaseId customerPurchaseId) {
        this.customerPurchaseId = customerPurchaseId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getUserid() {
        return userid;
    }
}
