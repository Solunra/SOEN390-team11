import React, { useState , useEffect} from 'react';
import request from 'superagent';
import BuildPath from '../RequestBuilder';
import {Grid, makeStyles} from "@material-ui/core";
import {ProductInventory} from "./ProductInventory";
import {PartInventory} from "./PartInventory";
import {MaterialInventory} from "./MaterialInventory";

const useStyles = makeStyles(theme => ({
    container: {
        marginTop: '50px',
    },
    rootGrid: {
        flexGrow: 1,
    },

}))
const Inventory = ()=>
{
    const classes = useStyles();
    const [prodInv, setProdInv]= useState([]);
    const [partInv, setPartInv]= useState([]);
    const [matInv, setMatInv]= useState([]);
    const getProdInv = () =>{
        request
            .get(BuildPath("/inventory/products/"))
            .set('Authorization', localStorage.getItem("Authorization"))
            .set('Accept', 'application/json')
            .then(res => {
                if (res.status === 200)
                {
                    var prolist = JSON.stringify(res.body);
                    if(JSON.stringify(prodInv) !== prolist){
                        setProdInv(res.body);
                        console.log(prodInv);
                    }
                }
            })
            .catch(err => {
                console.log(err);
            });
    }
    const getMatInv = () =>{
        request
            .get(BuildPath("/inventory/material/"))
            .set('Authorization', localStorage.getItem("Authorization"))
            .set('Accept', 'application/json')
            .then(res => {
                if (res.status === 200)
                {
                    if(JSON.stringify(matInv) !== JSON.stringify(res.body)){
                        setMatInv(res.body);
                    }
                }
            })
            .catch(err => {
                console.log(err);
            });
    }
    const getPartInv = () =>{
        request
            .get(BuildPath("/inventory/part/"))
            .set('Authorization', localStorage.getItem("Authorization"))
            .set('Accept', 'application/json')
            .then(res => {
                if (res.status === 200)
                {
                    if(JSON.stringify(partInv) !== JSON.stringify(res.body)){
                        setPartInv(res.body);
                    }
                }
            })
            .catch(err => {
                console.log(err);
            });
    }
    useEffect(()=>{
        getProdInv();
    },[prodInv]);
    useEffect(()=>{
        getPartInv();
    },[partInv]);
    useEffect(()=>{
        getMatInv();
    },[matInv]);
    return(
        <>
            <div className={classes.rootGrid}>
                <Grid container spacing={3}>
                    <Grid item xs={12} className={classes.container}>
                        <ProductInventory rows={prodInv}/>
                    </Grid>
                    <Grid item xs={12}>
                        <PartInventory  rows={partInv}/>
                    </Grid>
                    <Grid item xs={12}>
                        <MaterialInventory rows={matInv}/>
                    </Grid>
                </Grid>
            </div>
        </>
    );
}
export {Inventory};