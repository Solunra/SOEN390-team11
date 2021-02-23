CREATE TABLE part_inventory(
   id bigint auto_increment not null,
   location varchar(255),
   quantity INT,
   partid bigint not null unique,
   PRIMARY KEY(id),
   FOREIGN KEY (partid)
       REFERENCES part(partid)
);
CREATE TABLE material_inventory(
   id bigint auto_increment not null,
   location varchar(255),
   quantity INT,
   materialid bigint not null unique,
   PRIMARY KEY(id),
   FOREIGN KEY (materialid) REFERENCES material(materialid)
);
