import React from "react";
import TextField from "@material-ui/core/TextField";
import { Grid } from "@material-ui/core";

const OrderForm = (props) => {
    const { setQuantity, setDateTime, orderData } = props;

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
                // defaultValue={rowData['name']}
                type="number"
                onChange={(e) => setQuantity(e.target.value)}
                label={`Quantity ${orderData["unit"]}`}
                fullWidth
                variant="outlined"
            />
            <TextField
                id="datetime-local"
                label="Schedule Delivery"
                type="datetime-local"
                InputLabelProps={{
                    shrink: true,
                }}
                onChange={(e) => setDateTime(e.target.value)}
            />
        </Grid>
    );
};
export { OrderForm };
