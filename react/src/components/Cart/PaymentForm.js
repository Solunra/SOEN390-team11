import React, { useState } from 'react'
import Typography from '@material-ui/core/Typography'
import Grid from '@material-ui/core/Grid'
import TextField from '@material-ui/core/TextField'
import Dialog from "@material-ui/core/Dialog";
import DialogTitle from "@material-ui/core/DialogTitle";
import DialogContent from "@material-ui/core/DialogContent";
import DialogActions from "@material-ui/core/DialogActions";
import Button from "@material-ui/core/Button";
import request from "superagent";
import BuildPath from "../RequestBuilder";
import {FormControl, InputLabel, MenuItem, Select} from "@material-ui/core";

const PaymentForm = ({ open,handleClose,getPaymentsList,mode,payment,setPayment}) => {

  const [error, setError] = useState('')
  const [cardname,setCardname]=useState('');
  const [cardnumber,setCardnumber]=useState('');
  const [expDate, setExpDate] = useState('');
  const [cvv, setCvv]=useState('');
  const [type, setType]=useState('');
  const typeList= ["Credit", "Debit"];
  const cardNumberLength = 16
  const closeError = () => {
    setTimeout(() => {
      setError('')
    }, 5000)
  }
  const validation = (e, type) => {
    let value = e.target.value
    if (value.trim() === '') {
      return
    }
    switch (type) {
      case 0:
        setCardname(value);
        return
      case 1:
        if (!isNaN(value)) {
          setCardnumber(value);
          // setPayment(prev => new Map([...prev, ['cardnumber', e.target.value]]))
        } else {
          setError('Card Number have to be number')
          closeError()
        }
        return
      case 2:
        if (!isNaN(value)) {
          if (value.length === 4) {
            const month = Math.trunc(value / 100)
            const year = value % 100
            const curyear = new Date().toLocaleDateString('en', {
              year: '2-digit'
            })
            const curmonth = new Date().toLocaleDateString('en', {
              month: '2-digit'
            })
            if (
              month > 0 &&
              month < 13 &&
              month > curmonth &&
              year >= curyear
            ) {
              setExpDate(value);
              // setPayment(prev => new Map([...prev, ['exp', e.target.value]]))
            }
          }
        } else {
          setError('Invalid Expired date')
          closeError()
        }
        return
      case 3:
        if (!isNaN(value)) {
          setCvv(value)
          // setPayment(prev => new Map([...prev, ['cvv', e.target.value]]))
        } else {
          setError('CVV has to be number')
          closeError()
        }
        return
    }
  }
  const handleAdd=()=>{
    alert(!checkValue())
    if(!checkValue())
      return;
    request
        .put(BuildPath('/customer/payment'))
        .set('Authorization', localStorage.getItem('Authorization'))
        .send({
          "type":type,
          "cardName":cardname,
          "cardNum":cardnumber,
          "expireDate":expDate,
          "cvc":cvv
        })
        .set('Accept', 'application/json')
        .catch(err => {
          console.log(err.rawResponse)
          console.error(err)
        })
    getPaymentsList();
    alert("update payment")
    close();
  }
  const checkValue=()=>{
      if(!cardnumber || !type || !cardname || !cvv ||!expDate )
        return false;
      return true;
  }
  const clearValue=()=>{
    setType('');
    setExpDate('');
    setCardnumber('');
    setCardname('');
    setCvv('');

  }
  const close=()=>{
    clearValue();
    handleClose();
  }

  return (
    <>
      <Dialog
          open={open}
          onClose={close}
          aria-labelledby='form-dialog-title'
      >
        <DialogTitle id='form-dialog-title'>
          Add/Edit address
        </DialogTitle>
        <DialogContent>
        <Grid container spacing={3}>
          <Grid item xs={12}>
            <div style={{ color: 'red' }}>{error}</div>
          </Grid>
          <Grid item xs={12} md={6}>
            <TextField
              defaultValue={cardname === ''? payment['cardName']:cardname}
              required
              id='cardName'
              label='Name on card'
              fullWidth
              onChange={e => {
                validation(e, 0)
              }}

            />
          </Grid>
          <Grid item xs={12} md={6}>
            <FormControl variant="outlined" fullWidth >
              <InputLabel>Type</InputLabel>
              <Select
                  onChange={e =>{setType(e.target.value)}}
                  label="Vendor"
              >
                {typeList.map((row) =>{
                  return <MenuItem value={row} >{row}</MenuItem>;
                })}
              </Select>
            </FormControl>
          </Grid>
          <Grid item xs={12} md={6}>
            <TextField
              defaultValue={cardnumber === ''? payment['cardNum']:cardnumber}
              required
              id='cardNumber'
              label='Card number'
              fullWidth
              onChange={e => {
                validation(e, 1)
              }}
              InputProps={{
                inputProps: {
                  maxLength: 16
                }
              }}
              type={(mode === "edit" &&cardnumber==='')?"password":"text"}
            />
          </Grid>
          <Grid item xs={12} md={6}>
            <TextField
              defaultValue={expDate === ''? payment['expireDate']:expDate}
              required
              id='expDate'
              label='Expiry date'
              fullWidth
              InputProps={{
                inputProps: {
                  maxLength: 4
                }
              }}
              onChange={e => {
                validation(e, 2)
              }}
              type={(mode === "edit" &&expDate==='')?"password":"text"}
            />
          </Grid>
          <Grid item xs={12} md={6}>
            <TextField
              defaultValue={cvv === ''? payment['cvc']:cvv}
              required
              id='cvv'
              label='CVV'
              helperText='Last three digits on signature strip'
              fullWidth
              InputProps={{
                inputProps: {
                  maxLength: 3
                }
              }}
              onChange={e => {
                validation(e, 3)
              }}
              type={(mode === "edit" &&cvv==='')?"password":"text"}
            />
          </Grid>
        </Grid>
        </DialogContent>
        <DialogActions style={{ justifyContent: 'center' }}>
          <Button onClick={close} color='default'>
            Cancel
          </Button>
          <Button
              onClick={() => {
                handleAdd()
              }}
              color='primary'
          >
            Save
          </Button>
        </DialogActions>
      </Dialog>
    </>
  )
}
export default PaymentForm;
