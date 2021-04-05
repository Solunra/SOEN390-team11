CREATE TABLE payment(
    type varchar (100),
    cardName varchar (100),
    cardNum int (100),
    expireDate TIMESTAMP,
    cvc int (100),
    PRIMARY KEY (cardNum)
)