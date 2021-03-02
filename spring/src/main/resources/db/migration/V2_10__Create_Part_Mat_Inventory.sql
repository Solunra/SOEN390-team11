CREATE TABLE part_inventory(
   id CHAR(20) not null,
   location varchar(255),
   quantity INT,
   partid CHAR(20) not null unique,
   PRIMARY KEY(id),
   FOREIGN KEY (partid)
       REFERENCES part(partid)
);
CREATE TABLE material_inventory(
   id CHAR(20) not null,
   location varchar(255),
   quantity INT,
   materialid CHAR(20) not null unique,
   PRIMARY KEY(id),
   FOREIGN KEY (materialid) REFERENCES material(materialid)
);
