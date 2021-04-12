import React, {useEffect, useState} from 'react';
import {DataPie} from "./Pie";
import Button from "@material-ui/core/Button";
import {Grid, makeStyles} from "@material-ui/core";
import {DataTable} from "./Table";
import request from "superagent";
import BuildPath from "../RequestBuilder";

const useStyles = makeStyles((theme) => ({
    rootGrid: {
        flexGrow: 1,
        width: "100%",
        height: "100%",
        marginTop: "50px",
    },
    button: {
        backgroundColor: "#66ccff",
    },
}));

const RevenueExpense= (props)=>{
    const {dataJson,mode}=props;
    const classes = useStyles();
    const [page, setPage]=useState('table');

    const togglePieTable=()=>{
        if(page === "table") {
            setPage("pie");
        }
        else{
            setPage("table");
        }
    }
    const checkPage=()=>{
        switch (page){
            case "pie":
                return <DataPie dataJson={dataJson}/>;
            case "table":
                return <DataTable dataJson={dataJson} name={mode===1?"Income":"Expense"}/>;
        }
    }
    const checkPageName=()=>{
        switch (page){
            case "pie":
                return "Table";
            case "table":
                return "Pie";
        }
    }
    return (
        <>
            <div className={classes.rootGrid}>
                <Grid container spacing={1}>
                    <Grid item md={3}>
                        <Button
                            onClick={() => {togglePieTable();}}
                            className={classes.button}
                        >
                            {checkPageName()}
                        </Button>
                    </Grid>
                    <Grid item xs={12}>
                        {checkPage()}
                    </Grid>
                </Grid>
            </div>
        </>
    );

};
export {RevenueExpense};