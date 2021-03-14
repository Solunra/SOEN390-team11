import {Grid, makeStyles} from "@material-ui/core";
import React, {useEffect, useState} from "react";
import {OrderTable} from "./OrderTable";


const useStyles = makeStyles(theme => ({

    rootGrid: {
        flexGrow: 1,
        width:"100%",
        height: '100%'
    },

}))
const Order = ()=>{
    const [orderList, setOrderList] = useState([]);
    const [re_render, setRe_render] = useState(false);
    //create data to display in the order table without back-end
    const data =[
        { title: 'Vendor', field: 'vendorname' },
        { title: 'Type', field: 'type' },
        { title: 'Name', field: 'name' },
        { title: 'Quantity', field: 'quantity'},
        { title: 'Status', field: 'unit' },
        {'vendorname':'vendor1', 'type':'raw material', 'name': 'handle', 'quantity':30},
        {'vendorname':'vendor1', 'type':'raw material', 'name': 'handle', 'quantity':30},
        {'vendorname':'vendor1', 'type':'raw material', 'name': 'handle', 'quantity':30},
        {'vendorname':'vendor1', 'type':'raw material', 'name': 'handle', 'quantity':30},

    ]
    const getOrder = () =>{
        // request
        //     .get(BuildPath("/rawmaterials/"))
        //     .set('Authorization', localStorage.getItem("Authorization"))
        //     .set('Accept', 'application/json')
        //     .then(res => {
        //         if (res.status === 200)
        //         {
        //             if(JSON.stringify(orderList) !== JSON.stringify(res.body)){
        //                 setOrderList(res.body);
        //             }
        //         }
        //     })
        //     .catch(err => {
        //         console.log(err);
        //     });
    };
    useEffect(() => {
        //in case if re_rander pages have been modified, call it one more time.
        //it will get order back.
        getOrder();
    },[re_render]);
    const classes = useStyles();
    return(
        <>
            <div className={classes.rootGrid}>
                <Grid container spacing={3}>
                    <Grid item xs={12}>
                        <OrderTable
                            rows={data}
                            //reload pages when the system read the data
                            re_render={re_render}
                            setRe_render={setRe_render}
                        />
                    </Grid>
                </Grid>
            </div>
        </>
    );
}
export {Order};