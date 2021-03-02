CREATE TABLE product_inventory(
    id CHAR(20) not null,
    location varchar(255),
    quantity INT,
    productID CHAR(20) not null unique,
    PRIMARY KEY(id),
    CONSTRAINT FK_ProductID FOREIGN KEY (productID)
        REFERENCES product(productid)
);