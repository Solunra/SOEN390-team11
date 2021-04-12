import { Grid, makeStyles } from "@material-ui/core";
import React, { useState } from "react";
import {CustomTable} from "../Utils/CustomTable";

const useStyles = makeStyles((theme) => ({
    rootGrid: {
        flexGrow: 1,
        width: "100%",
        height: "100%",
    },
}));

const DataTable = ({dataJson, name}) => {
    const classes = useStyles();

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
