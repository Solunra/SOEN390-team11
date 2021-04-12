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

const TopProductTable = () => {
    const classes = useStyles();
    const dataJson = [
        {"month":["January"],"amount":["100"]},
        {"month":["February"],"amount":["100"]},
        {"month":["March"],"amount":["100"]},
        {"month":["April"],"amount":["100"]},
        {"month":["May"],"amount":["100"]},
        {"month":["June"],"amount":["100"]},
        {"month":["July"],"amount":["100"]},
        {"month":["August"],"amount":["100"]},
        {"month":["September"],"amount":["100"]},
        {"month":["October"],"amount":["100"]},
        {"month":["November"],"amount":["100"]},
        {"month":["December"],"amount":["100"]},
    ]
    const columns = [
        { title: "Month", field: "month" },
        { title: "Amount", field: "amount" },
    ];
    return (
        <>

            <CustomTable
                data={dataJson}
                columns={columns}
                title={`Top Product Table `}
            ></CustomTable>

        </>
    );
};
export { TopProductTable };
