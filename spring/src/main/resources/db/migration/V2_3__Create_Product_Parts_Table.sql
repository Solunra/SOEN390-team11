CREATE TABLE product_parts(
    partname varchar(255)  not null,
    productid bigint not null,
    PRIMARY KEY (partname,productid),
    CONSTRAINT FK_Part FOREIGN KEY (partname)
                REFERENCES part(name),
    CONSTRAINT FK_Product FOREIGN KEY (productid)
            REFERENCES product(productid)
);