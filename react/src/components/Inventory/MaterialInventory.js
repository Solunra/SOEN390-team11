import React from "react";
import { CustomTable } from "../Utils/CustomTable";

const MaterialInventory = (props) => {
    const { rows } = props;
    const columns = [
        { title: "Material Name", field: "materialname" },
        { title: "Quantity", field: "quantity" },
        { title: "Location", field: "location" },
    ];

    return (
        <CustomTable
            data={rows}
            columns={columns}
            title={`Material Inventory`}
        ></CustomTable>
    );
};
export { MaterialInventory };
