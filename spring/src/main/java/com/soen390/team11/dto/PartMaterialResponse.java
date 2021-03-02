package com.soen390.team11.dto;

import javax.persistence.Column;

public class PartMaterialResponse {
    private String id;
    private String partId;
    private String materialId;
    private Integer materialQuantity;
    private String materialname;

    public PartMaterialResponse() {
    }

    public PartMaterialResponse(String id, String partId, String materialId, Integer materialQuantity, String materialname) {
        this.id = id;
        this.partId = partId;
        this.materialId = materialId;
        this.materialQuantity = materialQuantity;
        this.materialname = materialname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPartId() {
        return partId;
    }

    public void setPartId(String partId) {
        this.partId = partId;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public Integer getMaterialQuantity() {
        return materialQuantity;
    }

    public void setMaterialQuantity(Integer materialQuantity) {
        this.materialQuantity = materialQuantity;
    }

    public String getMaterialname() {
        return materialname;
    }

    public void setMaterialname(String materialname) {
        this.materialname = materialname;
    }
}
