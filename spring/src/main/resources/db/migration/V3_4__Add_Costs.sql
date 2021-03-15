-- ALTER TABLE rawmaterial ADD cost FLOAT(2);
ALTER TABLE material_rawmaterials ADD qty INT;
ALTER TABLE product ADD cost FLOAT(2);
ALTER TABLE product ADD price FLOAT(2);

-- AVOID NULL VALUES
UPDATE product SET cost=0.0, price=0.0 WHERE productid LIKE 'prod-_';