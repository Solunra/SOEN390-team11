package com.soen390.team11.dto;



public class ProductInventoryResponse {
    private String prodinvid;
    private String location;
    private int quantity;
    private String productid;
    private String productname;

    public ProductInventoryResponse() {
    }

    public ProductInventoryResponse(String prodinvid, String location, int quantity, String productid, String productname) {
        this.prodinvid = prodinvid;
        this.location = location;
        this.quantity = quantity;
        this.productid = productid;
        this.productname = productname;
    }

    public String getProdinvid() {
        return prodinvid;
    }

    public void setProdinvid(String prodinvid) {
        this.prodinvid = prodinvid;
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

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }
}
