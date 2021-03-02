CREATE TABLE material_rawmaterials(
    materialid CHAR(20)  not null,
    rawmaterialid CHAR(20) not null,
    PRIMARY KEY(materialid, rawmaterialid),
    CONSTRAINT FK_Material FOREIGN KEY(materialid)
                REFERENCES material(materialid),
    CONSTRAINT FK_RawMaterial FOREIGN KEY(rawmaterialid)
            REFERENCES rawmaterial(rawmaterialid)
);