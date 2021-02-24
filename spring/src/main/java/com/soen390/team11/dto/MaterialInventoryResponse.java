package com.soen390.team11.dto;


public class MaterialInventoryResponse {
    private Long matInvid;
    private String location;
    private int quantity;
    private Long materialid;
    private String materialname;

    public MaterialInventoryResponse() {
    }

    public MaterialInventoryResponse(Long matInvid, String location, int quantity, Long materialid, String materialname) {
        this.matInvid = matInvid;
        this.location = location;
        this.quantity = quantity;
        this.materialid = materialid;
        this.materialname = materialname;
    }

    public Long getMatInvid() {
        return matInvid;
    }

    public void setMatInvid(Long matInvid) {
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

    public Long getMaterialid() {
        return materialid;
    }

    public void setMaterialid(Long materialid) {
        this.materialid = materialid;
    }

    public String getMaterialname() {
        return materialname;
    }

    public void setMaterialname(String materialname) {
        this.materialname = materialname;
    }
}
