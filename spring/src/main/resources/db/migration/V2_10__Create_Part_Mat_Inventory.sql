CREATE TABLE part_inventory(
   id varchar(72) not null,
   location varchar(255),
   quantity INT,
   partid varchar(72) not null unique,
   PRIMARY KEY(id),
   FOREIGN KEY (partid)
       REFERENCES part(partid)
);
CREATE TABLE material_inventory(
   id varchar(72) not null,
   location varchar(255),
   quantity INT,
   materialid varchar(72) not null unique,
   PRIMARY KEY(id),
   FOREIGN KEY (materialid) REFERENCES material(materialid)
);
