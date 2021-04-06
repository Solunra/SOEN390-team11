import React, { useState } from 'react'
import { makeStyles } from '@material-ui/core/styles'
import Typography from '@material-ui/core/Typography'
import Button from '@material-ui/core/Button'
import Breadcrumbs from '@material-ui/core/Breadcrumbs'
import NavigateNextIcon from '@material-ui/icons/NavigateNext'
import ShippingStep from './ShippingStep'
import PaymentForm from './PaymentForm'
import ReviewStep from './ReviewStep'
import {
  AppBar,
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
  Grid,
  Toolbar
} from '@material-ui/core'
import request from 'superagent'
import BuildPath from '../RequestBuilder'
import {PaymentStep} from "./PaymentStep";

const useStyles = makeStyles(theme => ({
  dialogWrapper: {
    width: '75%'
  },
  dialogAction: {
    justifyContent: 'flex-start'
  },
  leftDialogActions: {
    justifyContent: 'center'
  }
}))

const Checkout = ({ cartList, setCartList, isCheckoutOpen, closeAll }) => {
  // shipping address, payment handle here
  // check the payment shipping is missing or not
  const classes = useStyles()
  const [page, setPage] = useState(0)
  const [customerId, setCustomerId] = useState('')
  const [customerInfo, setCustomerInfo] = useState('')
  const [payment, setPayment] = useState(() => new Map())
  const [error, setError] = useState('')
  const [invoiceId, setInvoiceId] = useState('')
  const [totalPrice, setTotalPrice] = useState(0)

  const renderPage = () => {
    switch (page) {
      case 0:
        return (
          <ShippingStep
            setCustomerId={setCustomerId}
            setCustomerInfo={setCustomerInfo}
          />
        )
      case 1:
            return <PaymentStep />
      case 2:
        return (
          <ReviewStep
            invoiceId={invoiceId}
            customerInfo={customerInfo}
            payment={payment}
            cartList={cartList}
            totalPrice={totalPrice}
          />
        )
      default:
        return
    }
  }

  const checkPayment = () => {
    return (
      payment.has('name') &&
      payment.has('cardnumber') &&
      payment.has('exp') &&
      payment.has('cvv')
    )
  }

  const makePurchase = (amount, cart) => {
    request
      .put(BuildPath('/customer/purchase'))
      .set('Authorization', localStorage.getItem('Authorization'))
      .accept('text/plain')
      .send({
        customerID: customerId,
        totalamount: amount,
        cart: cart
      })
      .then(res => {
        setInvoiceId(res.text)
      })
      .catch(err => {
        console.error(err)
      })
  }

  const handleNext = () => {
    if (customerId === '') {
      setError('Please Select a Shipping Address. ')
      setTimeout(() => {
        setError('')
      }, 3000)
      return
    }
    setPage(page + 1)
  }

  const handleBack = () => {
    clearValue()
    setPage(page - 1)
  }

  const clearValue = () => {
    setCustomerId('')
    setPayment(new Map())
  }

  const completeTransaction = () => {
    if (!checkPayment()) {
      setError('All fields are required. ')
      setTimeout(() => {
        setError('')
      }, 3000)
      return
    }
    let submitCart = []
    let sumprice = 0
    for (let i = 0; i < cartList.length; i++) {
      submitCart = [
        ...submitCart,
        {
          productid: cartList[i]['product']['productid'],
          quantity: cartList[i]['count']
        }
      ]
      sumprice += cartList[i]['count'] * cartList[i]['product']['price']
    }
    setTotalPrice(sumprice)
    //set total payment so we can use in review too
    makePurchase(sumprice, submitCart)
    handleNext()
    // remove the cart list
    // store address ,store the order and payment
  }

  const handleClose = () => {
    closeAll()
    setPage(0)
    clearValue()
    if (page === 2) {
      setCartList([])
    }
    setInvoiceId('')
    setTotalPrice(0)
  }

  return (
    <>
      <Dialog
        open={isCheckoutOpen}
        onClose={handleClose}
        classes={classes.dialogWrapper}
        fullScreen
      >
        <AppBar>
          <Toolbar>
            <Typography variant='h6' className={classes.title}>
              Checkout
            </Typography>
          </Toolbar>
        </AppBar>
        <DialogTitle id='form-dialog-title'>Product Form</DialogTitle>
        <DialogContent>
          <Breadcrumbs
            separator={<NavigateNextIcon fontSize='small' />}
            aria-label='breadcrumb'
          >
            <Typography
              variant='h6'
              color={page === 0 ? 'textPrimary' : 'inherit'}
            >
              Shipping Information
            </Typography>
            <Typography
              variant='h6'
              color={page === 1 ? 'textPrimary' : 'inherit'}
            >
              Payment
            </Typography>
            <Typography
              variant='h6'
              color={page === 2 ? 'textPrimary' : 'inherit'}
            >
              Review
            </Typography>
          </Breadcrumbs>
          {renderPage()}
          <Grid item xs={12}>
            <div style={{ color: 'red' }}>{error}</div>
          </Grid>
        </DialogContent>
        <DialogActions classes={{ root: classes.leftDialogActions }}>
          {(page === 0 || page === 2) && (
            <Button variant='outlined' color='default' onClick={handleClose}>
              Close
            </Button>
          )}
          {page === 1 && (
            <Button variant='outlined' color='default' onClick={handleBack}>
              Back
            </Button>
          )}
          {page === 0 && (
            <Button
              variant='outlined'
              color='primary'
              onClick={handleNext}
              disabled={customerId === ''}
            >
              Next
            </Button>
          )}
          {page === 1 && (
            <Button
              variant='outlined'
              color='primary'
              onClick={completeTransaction}
            >
              Complete Transaction
            </Button>
          )}
        </DialogActions>
      </Dialog>
    </>
  )
}
export { Checkout }
