ALTER TABLE product_parts DROP FOREIGN KEY FK_Product;
ALTER TABLE product_parts ADD CONSTRAINT FK_Product FOREIGN KEY (productid)
    REFERENCES product(productid) ON DELETE CASCADE;
ALTER TABLE customer_purchase ADD userid varchar(150);
ALTER TABLE orders ADD userid varchar(150);
ALTER TABLE orders ADD ordertime TIMESTAMP;