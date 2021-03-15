package com.soen390.team11.dto;

import com.soen390.team11.entity.Customer;

import java.util.HashMap;
import java.util.List;

public class CustomerPurchaseDto {
    private String firstname;
    private String lastname;
    private String address;
    private String city;
    private String province;
    private String zip;
    private String country;
    private Double totalamount;
    private List<HashMap<String, Object>> carte;

    public CustomerPurchaseDto() {
    }

    public Double getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(Double totalamount) {
        this.totalamount = totalamount;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<HashMap<String, Object>> getCarte() {
        return carte;
//        ObjectMapper objectMapper= new ObjectMapper();
//        try {
//            carte = objectMapper.readValue(carteList,
//                    new TypeReference<List<HashMap<String, Object>>>(){});
//            System.out.println(carte.toString());
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        return null;
    }
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Customer getCustomer(){
        return new Customer(firstname,lastname,address,city,province,zip,country);
    }

}
