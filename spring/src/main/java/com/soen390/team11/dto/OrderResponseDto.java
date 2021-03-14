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

    public String getType() {
        return type;
    }

    public String getRawname() {
        return rawname;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getStatus() {
        return status;
    }
}
