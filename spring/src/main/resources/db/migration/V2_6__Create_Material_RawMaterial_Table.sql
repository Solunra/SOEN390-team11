CREATE TABLE material_rawmaterials(
    materialid varchar(72)  not null,
    rawmaterialid varchar(72) not null,
    PRIMARY KEY(materialid, rawmaterialid),
    CONSTRAINT FK_Material FOREIGN KEY(materialid)
                REFERENCES material(materialid),
    CONSTRAINT FK_RawMaterial FOREIGN KEY(rawmaterialid)
            REFERENCES rawmaterial(rawmaterialid) on delete cascade
);