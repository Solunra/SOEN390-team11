import React, { useEffect, useState } from "react";
import {
    Dialog,
    DialogTitle,
    DialogContent,
    makeStyles,
    DialogContentText,
    DialogActions,
    FormControl,
    InputLabel,
    Select,
    MenuList,
    Menu,
    MenuItem,
    Box,
    Input,
} from "@material-ui/core";
import Button from "@material-ui/core/Button";
import TextField from "@material-ui/core/TextField";
import request from "superagent";
import BuildPath from "../RequestBuilder";
import { Grid } from "@material-ui/core";
import { OrderForm } from "./OrderForm";
import { Confirmation } from "./Confirmation";

const useStyles = makeStyles((theme) => ({
    dialogWrapper: {
        width: "75%",
    },
    dialogAction: {
        justifyContent: "flex-start",
    },
    leftDialogActions: {
        justifyContent: "flex-start",
    },
}));

const Order = (props) => {
    const { open, setOpen, orderData } = props;
    const [quantity, setQuantity] = useState(0);
    const [dateTime, setDateTime] = useState("");
    const [error, setError] = useState("");
    const classes = useStyles();
    const [page, setPage] = useState(0);
    console.log(orderData);
    const checkPage = () => {
        switch (page) {
            case 0:
                return (
                    <OrderForm
                        orderData={orderData}
                        setDateTime={setDateTime}
                        setQuantity={setQuantity}
                    />
                );
            case 1:
                return (
                    <Confirmation
                        orderData={orderData}
                        quantity={quantity}
                        scheduledate={dateTime}
                    />
                );
        }
    };

    const checkValue = () => {
        if (
            quantity === 0 ||
            dateTime === "" ||
            new Date(dateTime) < Date.now()
        ) {
            return false;
        }
        return true;
    };
    const handleClose = () => {
        setOpen(false);
        setDateTime("");
        setPage(0);
    };
    const handleConfirm = () => {
        if (checkValue()) {
            setPage(page + 1);
        } else {
            setError("All field is required");
            setTimeout(() => {
                setError("");
            }, 3000);
        }
    };
    const handleBack = () => {
        setPage(page - 1);
    };
    const handleSubmit = () => {
        if (checkValue()) {
            request
                .post(BuildPath("/orders/create"))
                .set("Authorization", localStorage.getItem("Authorization"))
                .set("Accept", "application/json")
                .send({
                    vendorID: orderData["vendorID"],
                    saleID: orderData["rawmaterialid"],
                    quantity: quantity,
                    dateTime: new Date(dateTime),
                })
                .then((res) => {
                    console.log(JSON.stringify(res.body));
                    if (res.status === 200) {
                        handleClose();
                    }
                })
                .catch((err) => {
                    console.log(err);
                });
        }
    };

    return (
        <Dialog
            open={open}
            onClose={handleClose}
            aria-labelledby="form-dialog-title"
            classes={classes.dialogWrapper}
        >
            <DialogTitle id="form-dialog-title">Raw Material From</DialogTitle>
            <Grid item xs={12}>
                <div style={{ color: "red" }}>{error}</div>
            </Grid>
            <DialogContent>
                <DialogContentText> Place Order</DialogContentText>
                {checkPage()}
            </DialogContent>
            <DialogActions classes={{ root: classes.leftDialogActions }}>
                {page === 0 && (
                    <Button onClick={handleClose} color="primary">
                        Cancel
                    </Button>
                )}
                {page === 0 && (
                    <Button onClick={handleConfirm} color="primary">
                        Confirm
                    </Button>
                )}
                {page === 1 && (
                    <Button onClick={handleBack} color="primary">
                        Back
                    </Button>
                )}
                {page === 1 && (
                    <Button onClick={handleSubmit} color="primary">
                        Submit
                    </Button>
                )}
            </DialogActions>
        </Dialog>
    );
};
export { Order };
