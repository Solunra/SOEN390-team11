CREATE TABLE payment(
    pay_id varchar(150),
    pay_type varchar(150),
    card_name varchar(150),
    card_num varchar(150),
    expire_date varchar(150),
    cvc varchar(150),
    userID varchar(150),
    PRIMARY KEY (pay_id),
    CONSTRAINT user_payment_fk
        FOREIGN KEY (userID) REFERENCES user_account (userID)
);