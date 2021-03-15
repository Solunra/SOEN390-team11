package com.soen390.team11.dto;

/**
 * Response body for part inventory
 */
public class PartInventoryResponse {
    private String partInvid;
    private String location;
    private int quantity;
    private String partid;
    private String partname;

    public PartInventoryResponse() {
    }

    public PartInventoryResponse(String partInvid, String location, int quantity, String partid, String partname) {
        this.partInvid = partInvid;
        this.location = location;
        this.quantity = quantity;
        this.partid = partid;
        this.partname = partname;
    }

    public String getPartInvid() {
        return partInvid;
    }

    public void setPartInvid(String partInvid) {
        this.partInvid = partInvid;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPartid() {
        return partid;
    }

    public void setPartid(String partid) {
        this.partid = partid;
    }

    public String getPartname() {
        return partname;
    }

    public void setPartname(String partname) {
        this.partname = partname;
    }
}
