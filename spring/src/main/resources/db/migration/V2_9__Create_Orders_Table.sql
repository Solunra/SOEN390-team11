CREATE TABLE orders (
    vendorID char(255),
    orderID char(255),
    quantity int,
    time TIMESTAMP,
    PRIMARY KEY (orderID),
    FOREIGN KEY (vendorID) REFERENCES vendors(vendorID)
)