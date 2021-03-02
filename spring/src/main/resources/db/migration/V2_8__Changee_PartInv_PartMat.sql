DROP TABLE part_inventory;
CREATE TABLE part_material(
    id CHAR(20) PRIMARY KEY,
    part_id           CHAR(20) NOT NULL,
    material_id       CHAR(20) NOT NULL,
    material_quantity int    NOT NULL,
    FOREIGN KEY (material_id) REFERENCES team11.material (materialid),
    FOREIGN KEY (part_id) REFERENCES team11.part (partid)
)