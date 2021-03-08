import {Grid, makeStyles} from "@material-ui/core";
import React, {useEffect, useState} from "react";
import {RawMaterialTable} from "./RawMaterialTable";
import request from "superagent";
import BuildPath from "../RequestBuilder";

const useStyles = makeStyles(theme => ({

    rootGrid: {
        flexGrow: 1,
        width:"100%",
        height: '100%'
    },

}))
const RawMaterial = ()=>{
    const [rawMaterialList, setRawMaterialList] = useState([]);
    const [re_render, setRe_render] = useState(false);
    const getRawMaterial = () =>{
        request
            .get(BuildPath("/rawmaterials/"))
            .set('Authorization', localStorage.getItem("Authorization"))
            .set('Accept', 'application/json')
            .then(res => {
                if (res.status === 200)
                {
                    console.log(JSON.stringify(res.body))
                    if(JSON.stringify(rawMaterialList) !== JSON.stringify(res.body)){
                        setRawMaterialList(res.body);
                    }
                }
            })
            .catch(err => {
                console.log(err);
            });
    };
    useEffect(() => {
        getRawMaterial();
    },[re_render]);
    const classes = useStyles();
    return(
        <>
            <div className={classes.rootGrid}>
                <Grid container spacing={3}>
                    <Grid item xs={12}>
                        <RawMaterialTable
                            rows={rawMaterialList}
                            re_render={re_render}
                            setRe_render={setRe_render}
                        ></RawMaterialTable>
                    </Grid>
                </Grid>
            </div>
        </>
    );
}
export {RawMaterial};