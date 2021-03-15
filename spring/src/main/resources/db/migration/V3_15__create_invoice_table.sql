CREATE TABLE invoice (
    invoiceID char(255),
    purchasedate TIMESTAMP DEFAULT NOW(),
    paymentamount DOUBLE ,
    PRIMARY KEY (invoiceID)
)