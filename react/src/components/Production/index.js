import React, { useState , useEffect} from 'react';
import request from 'superagent';
import BuildPath from '../RequestBuilder';
import {ProductTable} from "./ProductTable";
import {ProductForm} from "./ProductForm";
import {Grid, makeStyles} from "@material-ui/core";
import {PartTable} from "./PartTable";
import {MaterialTable} from "./MaterialTable";
import {AlertErr} from "../Utils/AlertErr";

const useStyles = makeStyles(theme => ({
    container: {
        marginTop: '60px'
    },
    rootGrid: {
        flexGrow: 1,
    },
    paper: {
        padding: theme.spacing(2),
        textAlign: 'center',
        color: theme.palette.text.secondary,
    },
}))
const Production = ()=>
{
    const [productList, setProductList] = useState([]);
    const [re_render, setRe_render] = useState(false);
    const [data ,setData] = useState({});
    const [partTable ,setPartTable] = useState(false);
    const [materialTable ,setMaterialTable] = useState(false);
    const [part, setPart] = useState([]);
    const [partMaterial, setPartMaterial] = useState([]);
    const [errMessage, setErrMessage] = useState('');
    const [productPartTitle, setProductPartTitle] = useState('');
    const [partMaterialTitle, setPartMaterialTitle] = useState('');
    const classes = useStyles();

    const [open, setOpen] =useState(false);

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
                    if(JSON.stringify(productList) !== prolist){
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
        getPart();
    },[re_render]);

    const getPart = ()=>{
        request
            .get(BuildPath("/product/part"))
            .set('Authorization', localStorage.getItem("Authorization"))
            .set('Accept', 'application/json')
            .then(res => {
                if (res.status === 200)
                {
                    var partlist = JSON.stringify(res.body);
                    if(JSON.stringify(productList) !== partlist){
                        setPart(res.body);
                    }
                }
            })
            .catch(err => {
                console.log(err);
            });
    }
    const getPartMaterial = (id)=>{
        request
            .get(BuildPath("/parts/materials/"+id))
            .set('Authorization', localStorage.getItem("Authorization"))
            .set('Accept', 'application/json')
            .then(res => {
                if (res.status === 200)
                {
                    var partlist = JSON.stringify(res.body);
                    if(JSON.stringify(productList) !== partlist){
                        setPartMaterial(res.body);
                    }
                }
            })
            .catch(err => {
                console.log(err);
            });
    }

    const closeAlert = ()=>{
        setErrMessage('');
    }

    return(
        <>
            <div className={classes.rootGrid}>
                <Grid container spacing={3}>
                    <Grid item xs={12} className={classes.container}>
                        <ProductTable
                            rows={productList}
                            setOpen={setOpen}
                            re_render={re_render}
                            setRe_render={setRe_render}
                            setData={setData}
                            setErrMessage={setErrMessage}
                            setPartTable={setPartTable}
                            setProductPartTitle={setProductPartTitle}
                        />
                    </Grid>
                    <Grid item xs={12}>
                        {partTable && <PartTable
                            setPartTable={setPartTable}
                            setMaterialTable={setMaterialTable}
                            getPartMaterial={getPartMaterial}
                            rows={part}
                            setPartMaterialTitle={setPartMaterialTitle}
                            productPartTitle={productPartTitle}
                        />}
                    </Grid>
                    <Grid item xs={12}>
                        {materialTable && <MaterialTable
                            rows={partMaterial}
                            setMaterialTable={setMaterialTable}
                            partMaterialTitle={partMaterialTitle}
                        />}
                    </Grid>
                    <Grid item xs={12}>
                        {errMessage !=='' && <AlertErr message={errMessage} closeAlert={closeAlert}/>}
                    </Grid>
                    <Grid item xs={12}>
                        <ProductForm
                            open={open}
                            handleClose={handleClose}
                            data = {data}
                            re_render={re_render}
                            setRe_render={setRe_render}
                            setData={setData}
                            setErrMessage={setErrMessage}
                            />
                    </Grid>
                </Grid>
            </div>
        </>
    );
}
export {Production};