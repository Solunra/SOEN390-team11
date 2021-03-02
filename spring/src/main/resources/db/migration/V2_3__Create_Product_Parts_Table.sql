CREATE TABLE product_parts(
    partid CHAR(20)  not null,
    productid CHAR(20) not null,
    PRIMARY KEY (partid,productid),
    CONSTRAINT FK_Part FOREIGN KEY (partid)
                REFERENCES part(partid),
    CONSTRAINT FK_Product FOREIGN KEY (productid)
            REFERENCES product(productid)
);