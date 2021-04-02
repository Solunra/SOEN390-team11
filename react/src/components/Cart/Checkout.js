import React, { useEffect, useState } from 'react'
import { makeStyles } from '@material-ui/core/styles'
import Typography from '@material-ui/core/Typography'
import Button from '@material-ui/core/Button'
import Breadcrumbs from '@material-ui/core/Breadcrumbs'
import NavigateNextIcon from '@material-ui/icons/NavigateNext'
import ShippingForm from './ShippingForm'
import PaymentForm from './PaymentForm'
import { Review } from './Review'
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

const CheckOut = ({
  cartList,
  setCartList,
  openCheckOut,
  handleCloseCheckOut
}) => {
  // shipping address, payment handle here
  // check the payment shipping is missing or not
  const classes = useStyles()
  const [page, setPage] = useState(0)
  const [shipping, setShipping] = useState(() => new Map())
  const [payment, setPayment] = useState(() => new Map())
  const [error, setError] = useState('')
  const [invoiceId, setInvoiceId] = useState('')
  const [totalPrice, setTotalPrice] = useState(0)

  const checkPage = () => {
    switch (page) {
      case 0:
        return <ShippingForm setShipping={setShipping} />
      case 1:
        return <PaymentForm setPayment={setPayment} />
      case 2:
        return (
          <Review
            invoiceId={invoiceId}
            shipping={shipping}
            payment={payment}
            cartList={cartList}
            totalPrice={totalPrice}
          />
        )
      default:
        return
    }
  }
  const checkShipping = () => {
    if (
      shipping.has('firstname') &&
      shipping.has('lastname') &&
      shipping.has('city') &&
      shipping.has('province') &&
      shipping.has('zip') &&
      shipping.has('address') &&
      shipping.has('country')
    ) {
      if (
        shipping.get('firstname').trim() !== '' &&
        shipping.get('lastname').trim() !== '' &&
        shipping.get('city').trim() !== '' &&
        shipping.get('province').trim() !== '' &&
        shipping.get('zip').trim() !== '' &&
        shipping.get('address').trim() !== '' &&
        shipping.get('country').trim() !== ''
      ) {
        return true
      }
      return false
    }
    return false
  }
  const checkPayment = () => {
    if (
      payment.has('name') &&
      payment.has('cardnumber') &&
      payment.has('exp') &&
      payment.has('cvv')
    ) {
      return true
    }
    return false
  }

  const handleNext = () => {
    if (page === 0 && !checkShipping()) {
      setError('All fields are required. ')
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
    setShipping(new Map())
    setPayment(new Map())
  }
  const handleSubmit = () => {
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
    request
      .post(BuildPath('/customer/purchase/create'))
      .set('Authorization', localStorage.getItem('Authorization'))
      .set('Accept', 'application/json')
      .send({
        firstname: shipping.get('firstname'),
        lastname: shipping.get('lastname'),
        address: shipping.get('address'),
        city: shipping.get('city'),
        province: shipping.get('province'),
        zip: shipping.get('zip'),
        country: shipping.get('country'),
        totalamount: sumprice,
        cart: submitCart
      })
      .then(res => {
        if (res.status === 201) {
          setInvoiceId(res.body)
        }
      })
      .catch(err => {
        console.log(err)
      })
    handleNext()
    // remove the cart list
    // store address ,store the order and payment
  }
  const handleClose = () => {
    handleCloseCheckOut()
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
        open={openCheckOut}
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
        <DialogTitle id='form-dialog-title'>Product From</DialogTitle>
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
          {checkPage()}
          <Grid item xs={12}>
            <div style={{ color: 'red' }}>{error}</div>
          </Grid>
        </DialogContent>
        <DialogActions classes={{ root: classes.leftDialogActions }}>
          {(page === 0 || page === 2) && (
            <Button onClick={handleClose} color='primary'>
              Close
            </Button>
          )}
          {page === 1 && (
            <Button onClick={handleBack} color='primary'>
              Back
            </Button>
          )}
          {page === 0 && (
            <Button onClick={handleNext} color='primary'>
              Next
            </Button>
          )}
          {page === 1 && (
            <Button onClick={handleSubmit} color='primary'>
              Submit
            </Button>
          )}
        </DialogActions>
      </Dialog>
    </>
  )
}
export { CheckOut }
