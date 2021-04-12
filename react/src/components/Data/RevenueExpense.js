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

const RevenueExpense= ({mode})=>{
    const classes = useStyles();
    const [displayList , setDisplayList] = useState([]);
    const [page, setPage]=useState('table');
    const [loading, setLoading] =useState(true);
    const toggleMonthYear=()=>{
        if(page === "table"){
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
                return <DataTable dataJson={dataJson} name={mode===1?"Revenue":"Expense"}/>;
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
    const getData=()=>{
        // if page is expense get from order
        // if page is revenuse get from the customer purchase
        request
            .get(BuildPath("/product/costPrice"))
            .set("Authorization", localStorage.getItem("Authorization"))
            .set("Accept", "application/json")
            .then((res) => {
                console.log(res.body);
                if (
                    JSON.stringify(displayList) !==
                    JSON.stringify(res.body)
                ) {
                    setDisplayList(res.body);
                }
            })
            .catch((err) => {
                console.log(err);
            });
    }
    useEffect(()=>{
        getData();
    },[loading])


    // let color = randomColor();
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
    const displayName = 'BarExample';

    return (
        <>
            <div className={classes.rootGrid}>
                <Grid container spacing={1}>
                    <Grid item md={3}>
                        <Button
                            onClick={() => {toggleMonthYear();}}
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