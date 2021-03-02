DROP TABLE part_inventory;
CREATE TABLE part_material(
    id varchar(72) PRIMARY KEY,
    part_id           varchar(72) NOT NULL,
    material_id       varchar(72) NOT NULL,
    material_quantity int    NOT NULL,
    FOREIGN KEY (material_id) REFERENCES team11.material (materialid),
    FOREIGN KEY (part_id) REFERENCES team11.part (partid)
)