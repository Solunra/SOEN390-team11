ALTER TABLE rawmaterial ADD cost FLOAT(2);
ALTER TABLE material_rawmaterials ADD qty INT;
ALTER TABLE product ADD cost FLOAT(2) DEFAULT 0;
ALTER TABLE product ADD price FLOAT(2) DEFAULT 0;