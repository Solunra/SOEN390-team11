import { Grid, makeStyles } from "@material-ui/core";
import React, { useEffect, useState } from "react";
import request from "superagent";
import BuildPath from "../RequestBuilder";
import Button from "@material-ui/core/Button";
import { AccountPayable } from "./AccountPayable";
import { AccountReceivable } from "./AccountReceivable";
import { CustomizeReport } from "../Account/CustomizeReport";
import { ProductCostPrice } from "./ProductCostPrice";

const useStyles = makeStyles((theme) => ({
    rootGrid: {
        flexGrow: 1,
        width: "100%",
        height: "100%",
        marginTop: "50px",
    },
    button: {
        backgroundColor: "#66ccff",
    },
}));
const Account = () => {
    const [loading, setLoading] = useState(true);
    const [page, setPage] = useState(0);
    const [customizeReport, setCustomizeReport] = useState(false);
    const [payableList, setPayableList] = useState([]);
    const [receivableList, setReceivableList] = useState([]);
    const [proCostPriceList, setProCostPriceList] = useState([]);
    const closeCustomizeReport = () => {
        setCustomizeReport(false);
    };
    const getPayableList = () => {
        request
            .get(BuildPath("/orders/all"))
            .set("Authorization", localStorage.getItem("Authorization"))
            .set("Accept", "application/json")
            .then((res) => {
                if (res.status === 200) {
                    if (
                        JSON.stringify(payableList) !== JSON.stringify(res.body)
                    ) {
                        setPayableList(res.body);
                    }
                }
            })
            .catch((err) => {
                console.log(err);
            });
    };
    const getReceivableList = () => {
        request
            .get(BuildPath("/customer/account/allOrder"))
            .set("Authorization", localStorage.getItem("Authorization"))
            .set("Accept", "application/json")
            .then((res) => {
                if (res.status === 200) {
                    if (
                        JSON.stringify(receivableList) !==
                        JSON.stringify(res.body)
                    ) {
                        setReceivableList(res.body);
                    }
                }
            })
            .catch((err) => {
                console.log(err);
            });
    };
    const getProductCostPriceList = () => {
        request
            .get(BuildPath("/product/costPrice"))
            .set("Authorization", localStorage.getItem("Authorization"))
            .set("Accept", "application/json")
            .then((res) => {
                console.log(res.body);
                if (
                    JSON.stringify(proCostPriceList) !==
                    JSON.stringify(res.body)
                ) {
                    setProCostPriceList(res.body);
                }
            })
            .catch((err) => {
                console.log(err);
            });
    };
    useEffect(() => {
        getPayableList();
        getReceivableList();
        getProductCostPriceList();
    }, [loading]);
    const checkPage = () => {
        switch (page) {
            case 0:
                return <AccountPayable payableList={payableList} />;
            case 1:
                return <AccountReceivable receivableList={receivableList} />;
            case 2:
                return <ProductCostPrice proCostPriceList={proCostPriceList} />;
            default:
                return;
        }
    };
    const handleRefresh = () => {
        setPage(0);
        setLoading(!loading);
    };
    const refreshChangePage = () => {
        setPage((page + 1) % 3);
        if (page % 3 === 0 || page % 3 === 1) {
            setLoading(!loading);
        }
    };
    const getPageName = () => {
        switch (page) {
            case 0:
                return "Account Receivable";
            case 1:
                return "Product Cost Price";
            case 2:
                return "Account payable";
        }
    };

    const classes = useStyles();
    return (
        <div className={classes.rootGrid}>
            <Grid container spacing={1}>
                <Grid item md={3}>
                    <Button onClick={handleRefresh} className={classes.button}>
                        Refresh
                    </Button>
                </Grid>
                <Grid item md={3}>
                    <Button
                        onClick={() => refreshChangePage()}
                        className={classes.button}
                    >
                        {getPageName()}
                    </Button>
                </Grid>
                <Grid item md={3}>
                    <Button
                        onClick={() => setCustomizeReport(true)}
                        className={classes.button}
                    >
                        Customize Report
                    </Button>
                </Grid>

                <Grid item xs={12}>
                    {checkPage()}
                </Grid>
                <Grid item xs={12}>
                    {customizeReport && (
                        <CustomizeReport
                            open={customizeReport}
                            handleClose={closeCustomizeReport}
                            setPage={setPage}
                            setPayableList={setPayableList}
                            setReceivableList={setReceivableList}
                        ></CustomizeReport>
                    )}
                </Grid>
            </Grid>
        </div>
    );
};
export { Account };
