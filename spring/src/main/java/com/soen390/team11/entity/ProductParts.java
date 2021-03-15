package com.soen390.team11.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

/**
 * Database Entity for Product's Parts
 */
@Entity(name="product_parts")
public class ProductParts {

    @EmbeddedId
    private ProductPartsId productPartsId;

    /**
     * default constructor
     */
    public ProductParts() {

    }

    /**
     * constructor with param
     * 
     * @param productPartsId the desired embededid of the new isntance
     */
    public ProductParts(ProductPartsId productPartsId) {
        this.productPartsId = productPartsId;
    }

    /**
     * getter method for the productid and partid
     * 
     * @return the embeded object containing the productid and partid
     */
    public ProductPartsId getProductPartsId() {
        return productPartsId;
    }

    /**
     * mutator method for the productid and partid
     * 
     * @param productPartsId the desired productid and partid of the instance
     */
    public void setProductPartsId(ProductPartsId productPartsId) {
        this.productPartsId = productPartsId;
    }
}
