CREATE TABLE product(
    productid bigint auto_increment not null,
    name varchar(255),
    type varchar(255),
    size varchar(255),
    color varchar(255),
    finish varchar(255),
    grade varchar(255),
    PRIMARY KEY(productid)
);