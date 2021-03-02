CREATE TABLE product_inventory(
    id varchar(72) not null,
    location varchar(255),
    quantity INT,
    productID varchar(72) not null unique,
    PRIMARY KEY(id),
    CONSTRAINT FK_ProductID FOREIGN KEY (productID)
        REFERENCES product(productid)
);