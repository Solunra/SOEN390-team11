package com.soen390.team11.dto;

import javax.persistence.Column;

public class PartMaterialResponse {
    private Long id;
    private Long partId;
    private Long materialId;
    private Integer materialQuantity;
    private String materialname;

    public PartMaterialResponse() {
    }

    public PartMaterialResponse(Long id, Long partId, Long materialId, Integer materialQuantity, String materialname) {
        this.id = id;
        this.partId = partId;
        this.materialId = materialId;
        this.materialQuantity = materialQuantity;
        this.materialname = materialname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPartId() {
        return partId;
    }

    public void setPartId(Long partId) {
        this.partId = partId;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
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
