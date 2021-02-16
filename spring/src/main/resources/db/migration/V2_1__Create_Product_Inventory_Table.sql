CREATE TABLE product_inventory(
    id bigint auto_increment not null,
    location varchar(255),
    quantity INT,
    productID bigint not null unique,
    PRIMARY KEY(id),
    CONSTRAINT FK_ProductID FOREIGN KEY (productID)
        REFERENCES product(productid)
);