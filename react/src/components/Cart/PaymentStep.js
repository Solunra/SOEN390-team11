import React, {useEffect, useRef, useState} from "react";
import request from "superagent";
import BuildPath from "../RequestBuilder";
import List from "@material-ui/core/List";
import ListItem from "@material-ui/core/ListItem";
import AddressCard from "./AddressCard";
import Button from "@material-ui/core/Button";
import ShippingForm from "./ShippingForm";
import {createMuiTheme, makeStyles, MuiThemeProvider} from "@material-ui/core/styles";
import PaymentForm from "./PaymentForm";
import PaymentCard from "./PaymentCard";

const useStyles = makeStyles(theme => ({
    root: {
        '& > *': {
            margin: theme.spacing(1)
        }
    }
}))

const theme = createMuiTheme({
    overrides: {
        MuiListItem: {
            root: {
                '&': { width: 'initial' }
            }
        }
    }
})

const flexContainer = {
    display: 'flex',
    flexDirection: 'row',
    gap: '20px'
}
const PaymentStep =({setPayment})=>{
    const classes = useStyles()
    const [payments, setPayments] = useState([]);
    const [payId, setPayId] =useState('');
    const [open, setOpen]=useState(false);
    const getPaymentsList =()=>{
        request
            .get(BuildPath('/account/payments'))
            .set('Authorization', localStorage.getItem('Authorization'))
            .set('Accept', 'application/json')
            .then(res => {
                if (res.status === 200) {
                    setPayments(res.body)
                }
            })
            .catch(err => {
                console.log(err.rawResponse)
                console.error(err)
            })
    };
    const handleClose=()=>{
        setOpen(false);
    }

    useEffect(() => {
        if (open === false) getPaymentsList()
    }, [open])
    return(
        <MuiThemeProvider theme={theme}>
            <div className={classes.root}>
                <List style={flexContainer}>
                    {payments.map((payment, i) => (
                        <ListItem key={i}>
                            <PaymentCard
                                payment={payment}
                                payId={payId}
                                setPayId={setPayId}
                                setSelectedPay={setPayment}
                                getPaymentsList={getPaymentsList}
                            />
                        </ListItem>
                    ))}
                    <ListItem></ListItem>
                </List>
                <Button variant='outlined' onClick={()=>setOpen(true)}>
                    Add New Payment
                </Button>
                <PaymentForm
                    open={open}
                    handleClose={handleClose}
                    getPaymentsList={getPaymentsList}
                    setPayment={setPayment}
                    mode={"Add"}
                    payment={{}}
                />
            </div>

        </MuiThemeProvider>
    );
}
export {PaymentStep}