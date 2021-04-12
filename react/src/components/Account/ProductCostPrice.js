import React, { useEffect, useState } from "react";
import { CustomTable } from "../Utils/CustomTable";

const ProductCostPrice = (props) => {
    const { proCostPriceList } = props;
    const columns = [
        { title: "Product Description", field: "description" },
        { title: "Cost", field: "cost" },
        { title: "Price", field: "price" },
    ];
    return (
        <CustomTable
            data={proCostPriceList}
            columns={columns}
            title={`Product Cost Price Table`}
        ></CustomTable>
    );
};
export { ProductCostPrice };
