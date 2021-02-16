CREATE TABLE product_parts(
    partid bigint  not null,
    productid bigint not null,
    PRIMARY KEY (partid,productid),
    CONSTRAINT FK_Part FOREIGN KEY (partid)
                REFERENCES part(partid),
    CONSTRAINT FK_Product FOREIGN KEY (productid)
            REFERENCES product(productid)
);