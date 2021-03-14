package com.soen390.team11.dto;

import javax.persistence.EnumType;
import java.time.OffsetDateTime;

public class OrderResponseDto {
    private String vendorname;
    private String type;
    private String rawname;
    private int quantity;
    private String status;
    private String vendorID;
    private String rawID;
    private OffsetDateTime dateTime;

    public OrderResponseDto() {
    }

    public OrderResponseDto(String vendorname, String vendorID,String type, String rawname,  String rawID, int quantity, String status) {
        this.vendorname = vendorname;
        this.type = type;
        this.rawname = rawname;
        this.quantity = quantity;
        this.status = status;
        this.vendorID = vendorID;
        this.rawID = rawID;
    }

    public String getVendorname() {
        return vendorname;
    }

    public void setVendorname(String vendorname) {
        this.vendorname = vendorname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRawname() {
        return rawname;
    }

    public void setRawname(String rawname) {
        this.rawname = rawname;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
