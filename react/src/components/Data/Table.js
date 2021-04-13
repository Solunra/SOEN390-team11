import React, { useState } from "react";
import { CustomTable } from "../Utils/CustomTable";

const DataTable = ({ dataJson, name }) => {
    const columns = [
        { title: "Month", field: "month" },
        { title: "Amount", field: "amount" },
    ];
    return (
        <>
            <CustomTable
                data={dataJson}
                columns={columns}
                title={`${name} table`}
            ></CustomTable>
        </>
    );
};
export { DataTable };
