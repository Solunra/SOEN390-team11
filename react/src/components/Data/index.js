import React, {useState} from 'react';
import Button from "@material-ui/core/Button";
import {Grid, makeStyles} from "@material-ui/core";
import {Summary} from "./Summary";
import {RevenueExpense} from "./RevenueExpense";
import {TopProductTable} from "./TopProductTable";

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

const Data= ()=>{
    const classes = useStyles();
    const [page, setPage]=useState(0);
    const getPageName = () => {
        switch (page) {
            case 0:
                return "Revenue Report";
            case 1:
                return "Expense Report";
            case 2:
                return "Top Product";
            case 3:
                return "Summary";
        }
    };

    const checkPage=()=>{
        switch (page){
            case 0:
                return <Summary />;
            case 1:
                return <RevenueExpense mode={page}/>;
            case 2:
                return <RevenueExpense mode={page}/>;
            case 3:
                return <TopProductTable />;
        }
    }

        return (
            <>
                <div className={classes.rootGrid}>
                    <Grid container spacing={1}>
                        <Grid item md={3}>
                            <Button
                                onClick={() => {setPage((page+1)%4);}}
                                className={classes.button}
                            >
                                {getPageName()}
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
export {Data};