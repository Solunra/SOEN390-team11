import React, { useEffect, useState } from "react";
import Button from "@material-ui/core/Button";
import { Grid, makeStyles } from "@material-ui/core";
import { Summary } from "./Summary";
import { RevenueExpense } from "./RevenueExpense";
import { TopProductTable } from "./TopProductTable";
import request from "superagent";
import BuildPath from "../RequestBuilder";

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

const Data = () => {
    const classes = useStyles();
    const [page, setPage] = useState(0);
    const [loading, setLoading] = useState(true);
    const [summaryList, setSummaryList] = useState([]);
    const [topProdList, setTopProdList] = useState([]);
    const [incomeList, setIncomeList] = useState([]);
    const [expenseList, setExpenseList] = useState([]);
    const getIncomeList = () => {
        request
            .get(BuildPath("/data/income"))
            .set("Authorization", localStorage.getItem("Authorization"))
            .set("Accept", "application/json")
            .then((res) => {
                if (res.status === 200) {
                    if (
                        JSON.stringify(incomeList) !== JSON.stringify(res.body)
                    ) {
                        setIncomeList(res.body);
                    }
                }
            })
            .catch((err) => {
                console.log(err);
            });
    };
    const getExpenseList = () => {
        request
            .get(BuildPath("/data/expense"))
            .set("Authorization", localStorage.getItem("Authorization"))
            .set("Accept", "application/json")
            .then((res) => {
                if (res.status === 200) {
                    if (
                        JSON.stringify(expenseList) !== JSON.stringify(res.body)
                    ) {
                        setExpenseList(res.body);
                    }
                }
            })
            .catch((err) => {
                console.log(err);
            });
    };
    const getSummaryList = () => {
        request
            .get(BuildPath("/data/summary"))
            .set("Authorization", localStorage.getItem("Authorization"))
            .set("Accept", "application/json")
            .then((res) => {
                if (res.status === 200) {
                    if (
                        JSON.stringify(summaryList) !== JSON.stringify(res.body)
                    ) {
                        setSummaryList(res.body);
                    }
                }
            })
            .catch((err) => {
                console.log(err);
            });
    };
    const getTopProdList = () => {
        request
            .get(BuildPath("/data/topProduct"))
            .set("Authorization", localStorage.getItem("Authorization"))
            .set("Accept", "application/json")
            .then((res) => {
                if (res.status === 200) {
                    if (
                        JSON.stringify(topProdList) !== JSON.stringify(res.body)
                    ) {
                        setTopProdList(res.body);
                    }
                }
            })
            .catch((err) => {
                console.log(err);
            });
    };
    useEffect(() => {
        switch (page) {
            case 0:
                getSummaryList();
                break;
            case 1:
                getIncomeList();
                break;
            case 2:
                getExpenseList();
                break;
            case 3:
                getTopProdList();
                break;
        }
    }, [loading]);
    const getPageName = () => {
        switch (page) {
            case 0:
                return "Revenue Report";
            case 1:
                return "Expense Report";
            case 2:
                return "Top Product";
            case 3:
                return "Summary";
        }
    };

    const checkPage = () => {
        switch (page) {
            case 0:
                return <Summary dataJson={summaryList} />;
            case 1:
                return <RevenueExpense dataJson={incomeList} mode={page} />;
            case 2:
                return <RevenueExpense dataJson={expenseList} mode={page} />;
            case 3:
                return <TopProductTable dataJson={topProdList} />;
        }
    };

    const handleButtonPage = () => {
        setPage((page + 1) % 4);
        setLoading(!loading);
    };
    return (
        <>
            <div className={classes.rootGrid}>
                <Grid container spacing={1}>
                    <Grid item md={3}>
                        <Button
                            onClick={() => {
                                handleButtonPage();
                            }}
                            className={classes.button}
                        >
                            {getPageName()}
                        </Button>
                    </Grid>
                    <Grid item xs={12}>
                        {checkPage()}
                    </Grid>
                </Grid>
            </div>
        </>
    );
};
export { Data };
