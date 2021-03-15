package com.soen390.team11.entity;

import com.sun.istack.NotNull;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Database Object for Vendor ID and Sale IDs
 */
@Embeddable
public class VendorSaleId implements Serializable {

    @NotNull
    private String vendorID;

    @NotNull
    private String saleID;

    public VendorSaleId() {

    }

    public VendorSaleId(String vendorID, String saleID) {
        this.vendorID = vendorID;
        this.saleID = saleID;
    }

    public String getVendorID() {
        return vendorID;
    }

    public void setVendorID(String vendorID) {
        this.vendorID = vendorID;
    }

    public String getSaleID() {
        return saleID;
    }

    public void setSaleID(String saleID) {
        this.saleID = saleID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VendorSaleId that = (VendorSaleId) o;
        return vendorID.equals(that.vendorID) && saleID.equals(that.saleID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.vendorID, this.saleID);
    }
}
