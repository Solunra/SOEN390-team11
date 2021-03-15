-- AVOID NULL VALUES
UPDATE product SET cost=1000.0, price=1000.0 WHERE productid LIKE 'prod-_';
ALTER TABLE material_rawmaterials ADD qty INT;
ALTER TABLE product ADD cost FLOAT(2) DEFAULT 0;
ALTER TABLE product ADD price FLOAT(2) DEFAULT 0;
