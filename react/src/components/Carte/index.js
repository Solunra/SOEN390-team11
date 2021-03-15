import React, {useState} from 'react';
import {
    Dialog,
    DialogTitle,
    DialogContent,
    makeStyles,
    DialogActions,
    AppBar, Toolbar, IconButton
} from '@material-ui/core';
import Button from '@material-ui/core/Button';
import {CartItem} from "./CartItem";
import Typography from "@material-ui/core/Typography";


const useStyles = makeStyles(theme => ({
    dialogWrapper: {
        width: '75%'
    },
    dialogAction: {
        justifyContent: 'flex-start'
    },
    leftDialogActions: {
        justifyContent: 'flex-start'
    }
}))

const Cart = (props) =>{

    const {cartList,open, handleClose,handleIncrement,handleCheckOut,handleRemove} = props;
    const classes = useStyles();
    const calculateTotal = () =>{

        };
    return (
        <Dialog open={open} onClose={handleClose}  classes={ classes.dialogWrapper } fullScreen>
            <AppBar >
                <Toolbar>
                    <Typography variant="h6" className={classes.title}>
                        Cart
                    </Typography>
                </Toolbar>
            </AppBar>
            <DialogTitle id="form-dialog-title">Product From</DialogTitle>
            <DialogContent>

                {cartList.length === 0 ? <p>No items in cart.</p> : null}
                {cartList.map(item => (
                    <CartItem
                        item = {item}
                        handleIncrement={handleIncrement}
                        handleRemove={handleRemove}
                    />
                ))}
                {/*<h2>Total: ${calculateTotal(cartItems).toFixed(2)}</h2>*/}
            </DialogContent>
            <DialogActions classes={{ root: classes.leftDialogActions }}>
                <Button onClick={handleClose} color="primary">Close</Button>
                <Button onClick={handleCheckOut} color="primary">Check Out</Button>
            </DialogActions>
        </Dialog>
    )
}
export {Cart};