import React, { useState , useEffect} from 'react';
import request from 'superagent';
import BuildPath from '../RequestBuilder';
import {ProductTable} from "./ProductTable";
import {ProductForm} from "./ProductForm";
import IconButton from '@material-ui/core/IconButton';
import AddCircleRoundedIcon from '@material-ui/icons/AddCircleRounded';
import {makeStyles} from "@material-ui/core";


const useStyles = makeStyles(theme => ({
    container: {
        marginTop: '40px'
    }
}))
const Production = ()=>
{
    const [productList, setProductList] = useState([]);
    const [re_render, setRe_render] = useState(false);
    const [data ,setData] = useState({});
    const classes = useStyles();
    const HandleAddProduct = ()=>{
        setOpen(true);
    }
    // open is for the pop up screen for add product
    const [open, setOpen] = React.useState(false);

    const handleClose = () => {
        setOpen(false);
    };

    const getProducts = () =>{
        request
            .get(BuildPath("/product/"))
            .set('Authorization', localStorage.getItem("Authorization"))
            .set('Accept', 'application/json')
            .then(res => {
                if (res.status === 200)
                {
                    var prolist = JSON.stringify(res.body);
                    if(JSON.stringify(productList) !== prolist && prolist !== JSON.stringify([])){
                        setProductList(res.body);
                    }
                }
            })
            .catch(err => {
                console.log(err);
            });
    }
    useEffect(() => {
        getProducts();
    });
    useEffect(() => {
        getProducts();
    },[re_render]);


    return(
        <>
            <div className={classes.container}>
            <IconButton onClick={()=>HandleAddProduct()}>
               <AddCircleRoundedIcon/>
            </IconButton>
            <ProductTable
                rows={productList}
                setOpen={setOpen}
                re_render={re_render}
                setRe_render={setRe_render}
                setData={setData}
            />
            <ProductForm
                open={open}
                handleClose={handleClose}
                data = {data}
                re_render={re_render}
                setRe_render={setRe_render}
                setData={setData}
            />
            </div>

        </>
    );
}
export {Production};