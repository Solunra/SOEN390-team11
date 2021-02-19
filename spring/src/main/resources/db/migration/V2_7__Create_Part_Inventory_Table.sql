CREATE TABLE team11.part_inventory
(
    id bigint AUTO_INCREMENT
        PRIMARY KEY,
    name varchar(255) NOT NULL,
    material_id bigint NOT NULL,
    material_quantity int NOT NULL,
    CONSTRAINT part_inventory_material_materialid_fk
        FOREIGN KEY (material_id) REFERENCES team11.material (materialid)
);

CREATE INDEX part_inventory_name_index
    ON team11.part_inventory (name);