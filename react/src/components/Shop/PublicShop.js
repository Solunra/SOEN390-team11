import { Grid, makeStyles } from "@material-ui/core";
import React, { useEffect, useState } from "react";
import request from "superagent";
import BuildPath from "../RequestBuilder";
import { Product } from "./Product";

const useStyles = makeStyles((theme) => ({
    container: {
        margin: "60px auto auto auto",
        justifyContent: "center",
    },
    gridRoot: {
        flexGrow: 1,
    },
    wrapper: {
        display: "grid",
        gridTemplateColumns: "repeat(5, 1fr)",
        gap: "20px",
    },
    button: {
        backgroundColor: "#66ccff",
    },
}));

const PublicShop = () => {
    // retrieve all from database
    const [productList, setProductList] = useState([]);

    const getProducts = () => {
        request
            .get(BuildPath("/customer/public/allProduct"))
            .set("Authorization", localStorage.getItem("Authorization"))
            .set("Accept", "application/json")
            .then((res) => {
                if (res.status === 200) {
                    var prolist = JSON.stringify(res.body);
                    if (JSON.stringify(productList) !== prolist) {
                        setProductList(res.body);
                    }
                }
            })
            .catch((err) => {
                console.log(err);
            });
    };

    useEffect(() => {
        getProducts();
    });

    const classes = useStyles();
    return (
        <Grid container className={classes.wrapper} spacing={2}>
            {productList.map((row) => {
                return <Product product={row} role={"public"} />;
            })}
        </Grid>
    );
};
export { PublicShop };
