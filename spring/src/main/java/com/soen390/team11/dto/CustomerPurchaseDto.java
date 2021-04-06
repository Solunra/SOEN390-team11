package com.soen390.team11.dto;

import com.soen390.team11.entity.Customer;
import java.util.HashMap;
import java.util.List;

/**
 * customer purchase
 * information about the customer shipping
 * the cart of the their purchase
 */
public class CustomerPurchaseDto extends CustomerDto {

    private String customerID;
    private Double totalamount;
    private List<HashMap<String, Object>> cart;

    public CustomerPurchaseDto() {
    }

    public CustomerPurchaseDto(String firstname, String lastname, String address, String city,
        String province, String zip, String country, Double totalamount,
        List<HashMap<String, Object>> cart) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.city = city;
        this.province = province;
        this.zip = zip;
        this.country = country;
        this.totalamount = totalamount;
        this.cart = cart;
    }

    public Double getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(Double totalamount) {
        this.totalamount = totalamount;
    }

    public List<HashMap<String, Object>> getCart() {
        return cart;
    }

    @Override
    public Customer getCustomer() {
        return new Customer(firstname, lastname, address, city, province, zip, country);
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }
}
