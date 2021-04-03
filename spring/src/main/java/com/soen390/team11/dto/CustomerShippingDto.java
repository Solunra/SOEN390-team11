package com.soen390.team11.dto;

import com.soen390.team11.entity.Customer;

public class CustomerShippingDto extends CustomerDto {

    private String userId;

    public CustomerShippingDto() {
    }

    public CustomerShippingDto(String firstname, String lastname, String address, String city, String province, String zip, String country, String userId) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.city = city;
        this.province = province;
        this.zip = zip;
        this.country = country;
        this.userId = userId;
    }

    @Override
    public Customer getCustomer() {
        return new Customer(firstname,lastname,address,city,province,zip,country,userId);
    }
}
