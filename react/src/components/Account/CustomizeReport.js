import React, { useState } from "react";
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
} from "@material-ui/core";
import Button from "@material-ui/core/Button";
import TextField from "@material-ui/core/TextField";
import request from "superagent";
import BuildPath from "../RequestBuilder";
import { Grid } from "@material-ui/core";

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

const CustomizeReport = (props) => {
    const {
        open,
        handleClose,
        setPage,
        setPayableList,
        setReceivableList,
    } = props;
    const [startDate, setStartDate] = useState("");
    const [endDate, setEndDate] = useState("");
    const [reportType, setReportType] = useState("");
    const reportTypeList = ["None", "Sale", "Purchase"];
    const classes = useStyles();
    const handleSubmitCustomize = () => {
        let reportPath =
            reportType === "Sale"
                ? "customer"
                : reportType === "Purchase"
                ? "orders"
                : "";
        if (reportPath === "") {
            return;
        }
        request
            .post(BuildPath("/" + reportPath + "/report"))
            .set("Authorization", localStorage.getItem("Authorization"))
            .set("Accept", "application/json")
            .send({
                startDate: new Date(startDate),
                endDate: new Date(endDate),
            })
            .then((res) => {
                if (res.status === 200) {
                    console.log(reportType === "Sale");
                    if (reportType === "Sale") {
                        setReceivableList(res.body);
                        setPage(false);
                    }
                    if (reportType === "Purchase") {
                        setPayableList(res.body);
                        setPage(true);
                    }
                    clearValue();
                    handleClose();
                }
            })
            .catch((err) => {
                console.log(err);
            });
    };
    // submit to get the value and set the sale or purchase
    // validate the start before the today and end start before end date
    const clearValue = () => {
        setEndDate("");
        setStartDate("");
        setReportType("");
    };
    const handleCancel = () => {
        handleClose();
    };

    return (
        <Dialog
            open={open}
            onClose={handleClose}
            aria-labelledby="form-dialog-title"
            classes={classes.dialogWrapper}
        >
            <DialogTitle id="form-dialog-title">Raw Material From</DialogTitle>
            <DialogContent>
                <DialogContentText>
                    {" "}
                    Create or Edit Raw Material
                </DialogContentText>

                <FormControl variant="outlined" fullWidth>
                    <InputLabel>Report Type</InputLabel>
                    <Select
                        onChange={(e) => setReportType(e.target.value)}
                        label="Vendor"
                    >
                        {reportTypeList.map((row) => {
                            return <MenuItem value={row}>{row}</MenuItem>;
                        })}
                    </Select>
                </FormControl>

                <TextField
                    fullWidth
                    id="date"
                    label="Start Date"
                    type="date"
                    defaultValue={startDate}
                    onChange={(e) => setStartDate(e.target.value)}
                    InputLabelProps={{
                        shrink: true,
                    }}
                />
                <TextField
                    fullWidth
                    id="date"
                    label="End Date"
                    type="date"
                    defaultValue={startDate}
                    onChange={(e) => setEndDate(e.target.value)}
                    InputLabelProps={{
                        shrink: true,
                    }}
                />
            </DialogContent>
            <DialogActions classes={{ root: classes.leftDialogActions }}>
                <Button onClick={handleCancel} color="primary">
                    Cancel
                </Button>
                <Button onClick={handleSubmitCustomize} color="primary">
                    Submit
                </Button>
            </DialogActions>
        </Dialog>
    );
};
export { CustomizeReport };
