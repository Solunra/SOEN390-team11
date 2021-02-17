CREATE TABLE material_rawmaterials(
    materialid bigint  not null,
    rawmaterialid bigint not null,
    PRIMARY KEY(materialid, rawmaterialid),
    CONSTRAINT FK_Material FOREIGN KEY(materialid)
                REFERENCES material(materialid),
    CONSTRAINT FK_RawMaterial FOREIGN KEY(rawmaterialid)
            REFERENCES rawmaterial(rawmaterialid)
);