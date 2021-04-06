import React, { useState } from "react";
import ArrowForwardIcon from "@material-ui/icons/ArrowForward";
import CloseIcon from "@material-ui/icons/Close";
import { CustomTable } from "../Utils/CustomTable";
import BuildIcon from "@material-ui/icons/Build";

const PartTable = (props) => {
    const {
        rows,
        setMaterialTable,
        getPartMaterial,
        setPartTable,
        setPartMaterialTitle,
        productPartTitle,
    } = props;
    const columns = [{ title: "Part Name", field: "name" }];

    const actions = [
        {
            icon: () => {
                return <CloseIcon />;
            },
            position: "toolbar",
            export: false,
            onClick: () => {
                setPartTable(false);
            },
        },
        {
            icon: () => {
                return <ArrowForwardIcon />;
            },
            export: false,
            onClick: (event, rowData) => {
                setPartMaterialTitle(rowData["name"]);
                handleClickDetailMaterial(rowData["partid"]);
            },
        },
    ];
    const handleClickDetailMaterial = (id) => {
        getPartMaterial(id);
        setMaterialTable(true);
    };
    return (
        <>
            <CustomTable
                data={rows}
                columns={columns}
                actions={actions}
                title={`Part list table of ${productPartTitle}`}
            ></CustomTable>
        </>
    );
};
export { PartTable };
