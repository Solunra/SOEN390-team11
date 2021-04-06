import { Grid, makeStyles } from "@material-ui/core";
import { CustomerOrderTable } from "./CustomerOrderTable";
import React, { useState } from "react";

const useStyles = makeStyles((theme) => ({
    rootGrid: {
        flexGrow: 1,
        width: "100%",
        height: "100%",
    },
}));

const CustomerOrder = () => {
    const classes = useStyles();

    return (
        <>
            <div className={classes.rootGrid}>
                <Grid container spacing={3}>
                    <Grid item xs={12}>
                        <CustomerOrderTable></CustomerOrderTable>
                    </Grid>
                </Grid>
            </div>
        </>
    );
};
export { CustomerOrder };
