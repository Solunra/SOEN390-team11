import React from "react";
import { CustomTable } from "../Utils/CustomTable";

const Summary = (props) => {
    const { dataJson } = props;
    const columns = [
        { title: "Activity", field: "messageAction" },
        { title: "Value", field: "value" },
    ];
    return (
        <>
            <CustomTable
                data={dataJson}
                columns={columns}
                title={`Summary Table `}
            ></CustomTable>
        </>
    );
};
export { Summary };
