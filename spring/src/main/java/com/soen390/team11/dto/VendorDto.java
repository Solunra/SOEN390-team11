package com.soen390.team11.dto;

import com.soen390.team11.constant.Type;

public class VendorDto {

    private Type type;
    private String saleID;

    public VendorDto(Type type, String saleID) {
        this.type = type;
        this.saleID = saleID;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getSaleID() {
        return saleID;
    }

    public void setSaleID(String saleID) {
        this.saleID = saleID;
    }
}
