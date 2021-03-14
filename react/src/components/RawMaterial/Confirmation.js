import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Typography from '@material-ui/core/Typography';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemText from '@material-ui/core/ListItemText';
import Grid from '@material-ui/core/Grid';
import {
    Dialog,
    DialogTitle,
    DialogContent,
    DialogContentText,
    DialogActions,
    FormControl, InputLabel, Select, MenuList, Menu, MenuItem, Box, Input
} from '@material-ui/core';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';


const useStyles = makeStyles((theme) => ({
    listItem: {
        padding: theme.spacing(1, 0),
    },
    total: {
        fontWeight: 700,
    },
    title: {
        marginTop: theme.spacing(2),
    },
}));

const Confirmation =(props)=> {
    const classes = useStyles();
    const {orderData,quantity,scheduledate} =props;

    return (
        <>
        <Grid>
                <TextField
                    disabled
                    id="outlined-disabled"
                    margin="dense"
                    label="Name "
                    defaultValue={orderData['name']}
                    variant="outlined"
                    fullWidth
                />
                <TextField
                    disabled
                    margin="dense"
                    id="outlined-disabled"
                    label="Vendor Name"
                    defaultValue={orderData['companyname']}
                    variant="outlined"
                    fullWidth
                />
                <TextField
                    autoFocus
                    margin="dense"
                    disabled
                    type="number"
                    defaultValue={quantity}
                    label={`Quantity ${orderData['unit']}`}
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
                    defaultValue={orderData['price']*quantity}
                />
        </Grid>
        </>
    );
};
export {Confirmation};