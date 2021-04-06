import React from "react";
import { CustomTable } from "../Utils/CustomTable";

const ProductInventory = (props) => {
    const { rows } = props;
    const columns = [
        { title: "Material Name", field: "productname" },
        { title: "Quantity", field: "quantity" },
        { title: "Location", field: "location" },
    ];
    return (
        <>
            <CustomTable
                data={rows}
                columns={columns}
                title={`Product Inventory`}
            ></CustomTable>
        </>
    );
};
export { ProductInventory };
