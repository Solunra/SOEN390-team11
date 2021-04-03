package com.soen390.team11.dto;

import com.soen390.team11.entity.Customer;
import java.util.HashMap;
import java.util.List;

public class CustomerPurchaseDto extends CustomerDto {

    private Double totalamount;
    private List<HashMap<String, Object>> carte;

    public CustomerPurchaseDto() {
    }

    public CustomerPurchaseDto(String firstname, String lastname, String address, String city, String province, String zip, String country, Double totalamount, List<HashMap<String, Object>> carte) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.city = city;
        this.province = province;
        this.zip = zip;
        this.country = country;
        this.totalamount = totalamount;
        this.carte = carte;
    }

    public Double getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(Double totalamount) {
        this.totalamount = totalamount;
    }

    public List<HashMap<String, Object>> getCarte() {
        return carte;
    }

}
