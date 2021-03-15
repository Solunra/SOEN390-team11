package com.soen390.team11.entity;

import com.soen390.team11.constant.Type;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Database Entity for Vendor Sale ID
 */
@Entity(name = "vendor_sale")
public class VendorSale {

    @EmbeddedId
    VendorSaleId vendorSaleId;

    @Enumerated(EnumType.STRING)
    private Type type;

    public VendorSale() {

    }

    public VendorSale(VendorSaleId vendorSaleId, Type type) {
        this.vendorSaleId = vendorSaleId;
        this.type = type;
    }

    public VendorSaleId getVendorSaleId() {
        return vendorSaleId;
    }

    public void setVendorSaleId(VendorSaleId vendorSaleId) {
        this.vendorSaleId = vendorSaleId;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
