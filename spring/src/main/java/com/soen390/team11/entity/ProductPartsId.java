package com.soen390.team11.entity;

import com.sun.istack.NotNull;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProductPartsId implements Serializable {

    @NotNull
    private Long partid;

    @NotNull
    private Long productid;

    public ProductPartsId() {

    }

    public ProductPartsId(Long partid, Long productid) {
        this.partid = partid;
        this.productid = productid;
    }

    public Long getPartid() {
        return partid;
    }

    public void setPartid(Long partid) {
        this.partid = partid;
    }

    public Long getProductid() {
        return productid;
    }

    public void setProductid(Long productid) {
        this.productid = productid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductPartsId that = (ProductPartsId) o;
        return partid.equals(that.partid) && productid.equals(that.productid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(partid, productid);
    }
}
