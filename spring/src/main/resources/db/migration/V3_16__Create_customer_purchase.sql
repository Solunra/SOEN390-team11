CREATE TABLE customer_purchase (
    customerID char(255),
    productID char(255),
    invoiceID char(255),
    status char(255),
    amount integer(20),
    PRIMARY KEY (customerID, productID,invoiceID),
    FOREIGN KEY (customerID) REFERENCES customer(customerID) on delete cascade ,
    FOREIGN KEY (productID) REFERENCES product(productid),
    FOREIGN KEY (invoiceID) REFERENCES invoice(invoiceID) on delete cascade
)