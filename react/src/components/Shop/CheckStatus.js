import React, {useState} from 'react'
import {
  AppBar,
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
  Grid,
  ListItem,
  ListItemText,
  makeStyles,
  Toolbar
} from '@material-ui/core'
import Button from '@material-ui/core/Button'
import Typography from '@material-ui/core/Typography'
import TextField from '@material-ui/core/TextField'
import List from '@material-ui/core/List'
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
    gap: '20px',
    justifyContent: 'center'
  }
}))

const CheckStatus = ({ setOpenCheckStatus, openCheckStatus }) => {
  const [errMessage, setErrMessage] = useState('')
  const [invoiceId, setInvoiceId] = useState('')
  const [orderList, setOrderList] = useState([])
  const classes = useStyles()

  const handleCheckStatus = () => {
    if (invoiceId === '' || invoiceId.trim() === '') {
      setErrMessage('Invoice ID is required')
      setTimeout(() => {
        setErrMessage('')
      }, 3000)
      return
    }
    request
      .get(BuildPath('/customer/purchase/' + invoiceId))
      .set('Authorization', localStorage.getItem('Authorization'))
      .set('Accept', 'application/json')
      .then(res => {
        if (res.status === 200) {
          setOrderList(res.body)
          setInvoiceId('')
        }
      })
      .catch(err => {
        console.log(err)
        setInvoiceId('')
      })
  }
  const handleClose = () => {
    setOpenCheckStatus(false)
    setInvoiceId('')
    setOrderList([])
  }
  return (
    <Dialog
      open={openCheckStatus}
      onClose={handleClose}
      classes={classes.dialogWrapper}
      fullScreen
    >
      <AppBar>
        <Toolbar>
          <Typography variant='h6' className={classes.title}>
            Check your product status
          </Typography>
        </Toolbar>
      </AppBar>
      <DialogTitle id='form-dialog-title'>Product From</DialogTitle>
      <DialogContent>
        <TextField
          autoFocus
          margin='dense'
          value={invoiceId}
          onChange={e => setInvoiceId(e.target.value)}
          label='Invoice ID'
          fullWidth
          variant='outlined'
        />
        <Grid item xs={12}>
          <div style={{ color: 'red' }}>{errMessage}</div>
        </Grid>
        <Typography variant='h6' gutterBottom>
          Order summary
        </Typography>
        <List disablePadding>
          {orderList.size !== 0 &&
            orderList.map(row => (
              <ListItem key={row['id']}>
                <ListItemText
                  primary={`${row['name']}, ${row['color']}, ${row['size']}, ${row['finish']}, $${row['price']}`}
                />
                <Typography variant='body2'>{row['status']}</Typography>
              </ListItem>
            ))}
        </List>
      </DialogContent>
      <DialogActions classes={{ root: classes.leftDialogActions }}>
        <Button variant='outlined' color='secondary' onClick={handleClose}>
          Close
        </Button>
        <Button variant='outlined' color='primary' onClick={handleCheckStatus}>
          Check Status
        </Button>
      </DialogActions>
    </Dialog>
  )
}
export { CheckStatus }
