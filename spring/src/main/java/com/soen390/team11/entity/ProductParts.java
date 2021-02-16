package com.soen390.team11.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name="product_parts")
public class ProductParts {

    @EmbeddedId
    private ProductPartsId productPartsId;

    public ProductParts() {

    }

    public ProductParts(ProductPartsId productPartsId) {
        this.productPartsId = productPartsId;
    }

    public ProductPartsId getProductPartsId() {
        return productPartsId;
    }

    public void setProductPartsId(ProductPartsId productPartsId) {
        this.productPartsId = productPartsId;
    }


}
