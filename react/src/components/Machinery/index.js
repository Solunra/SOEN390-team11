import {Grid, makeStyles} from "@material-ui/core";
import {MachineryTable} from "./MachineryTable";
import React, {useState} from 'react';



const useStyles = makeStyles(theme => ({

    rootGrid: {
        flexGrow: 1,
        width:"100%",
        height: '100%'
    },

}))

const Machinery = () => {
    const [productList, setProductList] = useState([]);
    const classes = useStyles();


    const rows = [
        {"machineId":1, "productName": "bike123", "productid":"product 1"},
        {"machineId":2, "productName": "like Lust", "productid":"product 2"},
        {"machineId":3, "productName": "BMC", "productid":"product 3"},
        {"machineId":4, "productName": "Giant", "productid":"product 4"},
        {"machineId":5, "productName": "Canyon", "productid":"product 5"},
    ]

    return(
        <>
            <div className={classes.rootGrid}>
                <Grid container spacing={3}>
                    <Grid item xs={12}>
                        <MachineryTable
                            rows={productList}
                        ></MachineryTable>
                    </Grid>
                </Grid>
            </div>
        </>
    );
}
export {Machinery};