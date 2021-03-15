package com.soen390.team11.entity;

import com.sun.istack.NotNull;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Database Object for Product ID and Parts ID
 */
@Embeddable
public class ProductPartsId implements Serializable {

    @NotNull
    private String partid;

    @NotNull
    private String productid;

    public ProductPartsId() {

    }

    public ProductPartsId(String partid, String productid) {
        this.partid = partid;
        this.productid = productid;
    }

    public String getPartid() {
        return partid;
    }

    public void setPartid(String partid) {
        this.partid = partid;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
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
