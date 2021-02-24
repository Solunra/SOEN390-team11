package com.soen390.team11.dto;


public class PartInventoryResponse {
    private Long partInvid;
    private String location;
    private int quantity;
    private Long partid;
    private String partname;

    public PartInventoryResponse() {
    }

    public PartInventoryResponse(Long partInvid, String location, int quantity, Long partid, String partname) {
        this.partInvid = partInvid;
        this.location = location;
        this.quantity = quantity;
        this.partid = partid;
        this.partname = partname;
    }

    public Long getPartInvid() {
        return partInvid;
    }

    public void setPartInvid(Long partInvid) {
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

    public Long getPartid() {
        return partid;
    }

    public void setPartid(Long partid) {
        this.partid = partid;
    }

    public String getPartname() {
        return partname;
    }

    public void setPartname(String partname) {
        this.partname = partname;
    }
}
