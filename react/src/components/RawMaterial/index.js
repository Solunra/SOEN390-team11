import {Grid, makeStyles} from "@material-ui/core";
import React, {useState} from "react";
import {RawMaterialTable} from "./RawMaterialTable";

const useStyles = makeStyles(theme => ({

    rootGrid: {
        flexGrow: 1,
        width:"100%",
        height: '100%'
    },

}))
const RawMaterial = ()=>{

    const rows = [
        {"vendorID":1,"type":"Vendor1","saleID":"saleid 1"},
        {"vendorID":2,"type":"Vendor2","saleID":"saleid 2"},
        {"vendorID":3,"type":"Vendor3","saleID":"saleid 3"},
        {"vendorID":4,"type":"Vendor4","saleID":"saleid 4"},
    ]
    const [vendorList, setVendorList] = useState([]);
    const classes = useStyles();
    return(
        <>
            <div className={classes.rootGrid}>
                <Grid container spacing={3}>
                    <Grid item xs={12}>
                        <RawMaterialTable></RawMaterialTable>
                    </Grid>
                </Grid>
            </div>
        </>
    );
}
export {RawMaterial};