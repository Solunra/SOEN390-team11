CREATE TABLE team11.product_machinery
(
    id         varchar(255) NOT NULL
        PRIMARY KEY,
    name       varchar(255) NOT NULL,
    status     varchar(255) NOT NULL,
    timer      int          NOT NULL,
    product_id varchar(255) NULL,
    CONSTRAINT product_machinery_product_id_fk
        FOREIGN KEY (product_id) REFERENCES team11.product (productid)
);