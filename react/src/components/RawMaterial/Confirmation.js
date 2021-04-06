import React from "react";
import Grid from "@material-ui/core/Grid";
import TextField from "@material-ui/core/TextField";

const Confirmation = (props) => {
    const { orderData, quantity, scheduledate } = props;

    return (
        <Grid>
            <TextField
                disabled
                id="outlined-disabled"
                margin="dense"
                label="Name "
                defaultValue={orderData["name"]}
                variant="outlined"
                fullWidth
            />
            <TextField
                disabled
                margin="dense"
                id="outlined-disabled"
                label="Vendor Name"
                defaultValue={orderData["companyname"]}
                variant="outlined"
                fullWidth
            />
            <TextField
                autoFocus
                margin="dense"
                disabled
                type="number"
                defaultValue={quantity}
                label={`Quantity ${orderData["unit"]}`}
                fullWidth
                variant="outlined"
            />
            <TextField
                id="datetime-local"
                label="Schedule Delivery"
                type="datetime-local"
                disabled
                fullWidth
                margin="dense"
                variant="outlined"
                defaultValue={scheduledate}
            />
            <TextField
                id="datetime-local"
                label="Total Price"
                disabled
                margin="dense"
                variant="outlined"
                defaultValue={orderData["cost"] * quantity}
            />
        </Grid>
    );
};
export { Confirmation };
