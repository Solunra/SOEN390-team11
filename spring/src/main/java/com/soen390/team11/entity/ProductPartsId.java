package com.soen390.team11.entity;

import com.sun.istack.NotNull;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProductPartsId implements Serializable {

    @NotNull
    private String partname;

    @NotNull
    private Long productid;

    public ProductPartsId() {

    }

    public ProductPartsId(String partname, Long productid) {
        this.partname = partname;
        this.productid = productid;
    }

    public String getPartname() {
        return partname;
    }

    public void setPartname(String partname) {
        this.partname = partname;
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
        return partname.equals(that.partname) && productid.equals(that.productid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(partname, productid);
    }
}
