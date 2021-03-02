package com.soen390.team11.dto;


public class MaterialInventoryResponse {
    private String matInvid;
    private String location;
    private int quantity;
    private String materialid;
    private String materialname;

    public MaterialInventoryResponse() {
    }

    public MaterialInventoryResponse(String matInvid, String location, int quantity, String materialid, String materialname) {
        this.matInvid = matInvid;
        this.location = location;
        this.quantity = quantity;
        this.materialid = materialid;
        this.materialname = materialname;
    }

    public String getMatInvid() {
        return matInvid;
    }

    public void setMatInvid(String matInvid) {
        this.matInvid = matInvid;
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

    public String getMaterialid() {
        return materialid;
    }

    public void setMaterialid(String materialid) {
        this.materialid = materialid;
    }

    public String getMaterialname() {
        return materialname;
    }

    public void setMaterialname(String materialname) {
        this.materialname = materialname;
    }
}
