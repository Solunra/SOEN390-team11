CREATE TABLE vendor_sale (
    vendorID char(255),
    saleID char(255),
    type char(255),
    PRIMARY KEY (vendorID, saleID),
    FOREIGN KEY (vendorID) REFERENCES vendors(vendorID)
)