import { Grid, makeStyles } from "@material-ui/core";
import React, {useEffect, useState} from "react";
import {CustomTable} from "../Utils/CustomTable";
import request from "superagent";
import BuildPath from "../RequestBuilder";

const useStyles = makeStyles((theme) => ({
    rootGrid: {
        flexGrow: 1,
        width: "100%",
        height: "100%",
    },
}));

const TopProductTable = (props) => {
    const {dataJson}=props
    const classes = useStyles();
    const [displayList , setDisplayList] = useState([]);
    const columns = [
        { title: "Product description", field: "description" },
        { title: "Quantity", field: "amount" },
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
