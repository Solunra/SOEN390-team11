CREATE TABLE product_variant(
    productid bigint,
    variantid bigint,
    PRIMARY KEY(productid,variantid),
    FOREIGN KEY (productid) REFERENCES product(productid),
    FOREIGN KEY (variantid) REFERENCES variant(variantid)
);