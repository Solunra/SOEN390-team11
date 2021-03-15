import {Grid, makeStyles} from "@material-ui/core";
import React, {useEffect, useState} from "react";
import {OrderTable} from "./OrderTable";
import request from 'superagent';
import BuildPath from '../RequestBuilder'

const useStyles = makeStyles(theme => ({

    rootGrid: {
        flexGrow: 1,
        width:"100%",
        height: '100%'
    },

}))
const Order = ()=>{
    const [orderList, setOrderList] = useState([]);
    const [loading, setLoading] = useState(true);

    // const data=[
    //     // { title: 'Vendor', field: 'vendorname' },
    //     //         { title: 'Type', field: 'type' },
    //     //         { title: 'Type Name', field: 'name' },
    //     //         { title: 'Quantity', field: 'quantity'},
    //     //         { title: 'Status', field: 'unit' },
    //     {'vendorname':'vendor1', 'type': 'raw material', 'name': 'handle ','quantity':30 },
    //     {'vendorname':'vendor1', 'type': 'raw material', 'name': 'handle ','quantity':30 },
    //     {'vendorname':'vendor1', 'type': 'raw material', 'name': 'handle ','quantity':30 },
    //     {'vendorname':'vendor1', 'type': 'raw material', 'name': 'handle ','quantity':30 },
    // ]
    const getOrder = () =>{
        request
            .get(BuildPath("/orders/all"))
            .set('Authorization', localStorage.getItem("Authorization"))
            .set('Accept', 'application/json')
            .then(res => {
                if (res.status === 200)
                {
                    let temp = orderList;
                    if(JSON.stringify(temp) !== JSON.stringify(res.body)){
                        setOrderList(res.body);
                    }
                }
            })
            .catch(err => {
                console.log(err);
            });
    };
    useEffect(() => {
        getOrder();
    },[loading]);
    const classes = useStyles();
    return(
        <>
            <div className={classes.rootGrid}>
                <Grid container spacing={3}>
                    <Grid item xs={12}>
                        <OrderTable
                            rows={orderList}
                        />

                    </Grid>
                </Grid>
            </div>
        </>
    );
}
export {Order};