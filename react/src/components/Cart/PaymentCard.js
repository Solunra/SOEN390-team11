import React from 'react'
import { makeStyles } from '@material-ui/core/styles'
import Card from '@material-ui/core/Card'
import CardActions from '@material-ui/core/CardActions'
import CardContent from '@material-ui/core/CardContent'
import Radio from '@material-ui/core/Radio'
import Button from '@material-ui/core/Button'
import Typography from '@material-ui/core/Typography'
import request from 'superagent'
import BuildPath from '../RequestBuilder'
import ShippingForm from './ShippingForm'
import { DeleteForeverTwoTone, EditLocationTwoTone } from '@material-ui/icons'
import PaymentForm from "./PaymentForm";

const useStyles = makeStyles({
    root: {
        minWidth: 275,
        textAlign: 'center'
    },
    title: {
        fontSize: 14
    },
    pos: {
        marginBottom: 12
    },
    radio: {
        width: 'auto'
    },
    content: {
        fontSize: 12,
        '&:hover': {
            background: '#eee',
            cursor: 'pointer'
        }
    }
})

export default function PaymentCard ({payment,payId,setPayId,setSelectedPay,getPaymentsList}) {
    const classes = useStyles()
    const [open, setOpen] = React.useState(false)
    const handleClose = ()=>{
        setOpen(false);
    }
    const Select=(payment)=>{
        setPayId(payment['payId']);
        setSelectedPay(payment);
    }
    const deletePayment = () => {
        if(payId==='')
            return;
        request
            .delete(BuildPath('/customer/payment/' + payId))
            .set('Authorization', localStorage.getItem('Authorization'))
            .accept('application/json')
            .then(res=>{
                getPaymentsList();
                setPayId('');
                setSelectedPay({});
            })
            .catch(err => {
                console.error(err)
            })
    }

    return (
        <Card
            className={classes.root}
            variant='outlined'
            onClick={() => Select(payment)}
        >
            <div className={classes.content}>
                <Radio
                    className={classes.radio}
                    checked={payId === payment['payId']}
                    color='default'
                    name='radio-button'
                />
                <CardContent>
                    <Typography
                        className={classes.title}
                        color='textSecondary'
                        gutterBottom
                    >
                    </Typography>

                    <Typography variant='body2' component='p'>
                        {payment['cardName']}
                    </Typography>
                </CardContent>
            </div>
            <CardActions style={{ justifyContent: 'center' }}>
                <Button
                    variant='outlined'
                    onClick={() => {
                        deletePayment();
                    }}
                >
                    <DeleteForeverTwoTone size='small' style={{ color: 'darkred' }} />
                </Button>
            </CardActions>
        </Card>
    )
}
