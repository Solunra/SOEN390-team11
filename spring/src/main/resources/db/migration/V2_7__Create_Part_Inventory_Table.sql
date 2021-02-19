CREATE TABLE team11.part_inventory
(
    id                bigint AUTO_INCREMENT
        PRIMARY KEY,
    part_id           bigint NOT NULL,
    material_id       bigint NOT NULL,
    material_quantity int    NOT NULL,
    CONSTRAINT part_inventory_material_materialid_fk
        FOREIGN KEY (material_id) REFERENCES team11.material (materialid),
    CONSTRAINT part_inventory_part_partid_fk
        FOREIGN KEY (part_id) REFERENCES team11.part (partid)
);

CREATE INDEX part_inventory_name_index
    ON team11.part_inventory (part_id);

ALTER TABLE team11.part_inventory
    ADD UNIQUE unq_part_inventory_partid_materialid (part_id, material_id);