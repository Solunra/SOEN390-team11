DROP TABLE part_inventory;
CREATE TABLE part_material(
    id bigint AUTO_INCREMENT PRIMARY KEY,
    part_id           bigint NOT NULL,
    material_id       bigint NOT NULL,
    material_quantity int    NOT NULL,
    FOREIGN KEY (material_id) REFERENCES team11.material (materialid),
    FOREIGN KEY (part_id) REFERENCES team11.part (partid)
)