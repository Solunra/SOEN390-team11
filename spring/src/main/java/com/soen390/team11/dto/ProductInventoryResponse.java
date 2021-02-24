package com.soen390.team11.dto;



public class ProductInventoryResponse {
    private Long prodinvid;
    private String location;
    private int quantity;
    private Long productid;
    private String productname;

    public ProductInventoryResponse() {
    }

    public ProductInventoryResponse(Long prodinvid, String location, int quantity, Long productid, String productname) {
        this.prodinvid = prodinvid;
        this.location = location;
        this.quantity = quantity;
        this.productid = productid;
        this.productname = productname;
    }

    public Long getProdinvid() {
        return prodinvid;
    }

    public void setProdinvid(Long prodinvid) {
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

    public Long getProductid() {
        return productid;
    }

    public void setProductid(Long productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }
}
