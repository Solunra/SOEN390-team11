CREATE TABLE payment(
    pay_id varchar (100)
    `type` varchar (100),
    card_name varchar (100),
    card_num varchar (100),
    expire_date TIMESTAMP,
    cvc varchar (100),
    userID varchar (100),
    PRIMARY KEY (payid),
    CONSTRAINT user_payment_fk
        FOREIGN KEY (userID) REFERENCES user_account (userID);
);