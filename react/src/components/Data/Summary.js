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

const Summary = (props) => {
    const {dataJson}=props;
    const classes = useStyles();
    const [displayList , setDisplayList] = useState([]);
    const columns = [
        { title: "Product description", field: "description" },
        { title: "Quantity", field: "amount" },
    ];
    // const columns = [
    //     { title: "Activity", field: "actions" },
    //     { title: "Amount", field: "amount" },
    // ];
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
