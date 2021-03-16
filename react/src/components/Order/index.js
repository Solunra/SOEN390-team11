import {Grid, makeStyles} from "@material-ui/core";
import React, {useEffect, useState} from "react";
import {OrderTable} from "./OrderTable";
import {CustomerOrder} from "./CustomerOrder";
import request from 'superagent';
import BuildPath from '../RequestBuilder'
import Button from "@material-ui/core/Button";

const useStyles = makeStyles(theme => ({

    rootGrid: {
        flexGrow: 1,
        width:"100%",
        height: '100%',
        marginTop: '50px'
    },
    button:{
        backgroundColor: '#66ccff'
    }

}))
const Order = ()=>{
    const [orderList, setOrderList] = useState([]);
    const [customerorderList, setCustomerorderList] = useState([]);
    const [loading, setLoading] = useState(true);
    const [page, setPage] = useState(true);
    const checkPage= ()=>{
        switch (page) {
            case true:
                return <OrderTable
                    rows={orderList}
                />;
            case false:
                return <CustomerOrder
                    rows={customerorderList}
                    loading={loading}
                    setLoading={setLoading}
                />;
            default:
                return ;
        }
    }
    const getOrder = () =>{
        request
            .get(BuildPath("/orders/all"))
            .set('Authorization', localStorage.getItem("Authorization"))
            .set('Accept', 'application/json')
            .then(res => {
                if (res.status === 200)
                {
                    if(JSON.stringify(orderList) !== JSON.stringify(res.body)){
                        setOrderList(res.body);
                    }
                }
            })
            .catch(err => {
                console.log(err);
            });
    };
    const getCustomerOrder = () =>{
        request
            .get(BuildPath("/customer/allOrder"))
            .set('Authorization', localStorage.getItem("Authorization"))
            .set('Accept', 'application/json')
            .then(res => {
                if (res.status === 200)
                {
                    console.log(res.body);
                    if(JSON.stringify(customerorderList) !== JSON.stringify(res.body)){
                        setCustomerorderList(res.body);
                    }
                }
            })
            .catch(err => {
                console.log(err);
            });
    };
    useEffect(() => {
        getOrder();
        getCustomerOrder();
    },[loading]);
    const classes = useStyles();
    return(
        <>
            <div className={classes.rootGrid}>
                <Grid container spacing={3}>
                    <Grid item md={12}>
                        <Button onClick={()=>setPage(!page)} className={classes.button}>{!page?"Order Table": "Customer Order Table"}</Button>
                    </Grid>
                    <Grid item xs={12}>
                        {checkPage()}
                    </Grid>
                </Grid>
            </div>
        </>
    );
}
export {Order};