ALTER TABLE customer_shipping
    MODIFY firstname VARCHAR(255) NULL;

ALTER TABLE customer_shipping
    MODIFY lastname VARCHAR(255) NULL;

ALTER TABLE customer_shipping
    MODIFY address VARCHAR(255) NULL;

ALTER TABLE customer_shipping
    MODIFY city VARCHAR(50) NULL;

ALTER TABLE customer_shipping
    MODIFY province VARCHAR(50) NULL;

ALTER TABLE customer_shipping
    MODIFY zip VARCHAR(50) NULL;

ALTER TABLE customer_shipping
    MODIFY country VARCHAR(50) NULL;

ALTER TABLE customer_shipping
    ADD userID VARCHAR(255) NULL;

ALTER TABLE customer_shipping
    ADD CONSTRAINT customer_shipping_user_account_userID_fk
        FOREIGN KEY (userID) REFERENCES user_account (userID);