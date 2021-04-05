import React, {useState} from 'react'
import {
  AppBar,
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
  FormControl,
  Grid,
  InputLabel,
  makeStyles,
  MenuItem,
  Select,
  Toolbar
} from '@material-ui/core'
import Button from '@material-ui/core/Button'
import Typography from '@material-ui/core/Typography'
import request from 'superagent'
import BuildPath from '../RequestBuilder'
import {Product} from './Product'

const useStyles = makeStyles(theme => ({
  dialogWrapper: {
    width: '75%'
  },
  dialogAction: {
    justifyContent: 'flex-start'
  },
  leftDialogActions: {
    justifyContent: 'center',
    gap: '20px'
  },
  wrapper: {
    display: 'grid',
    gridTemplateColumns: 'repeat(5, 1fr)',
    gap: '20px',
    marginTop: '30px'
  }
}))

const Customize = props => {
  const { openCustomize, setOpenCustomize, handleAdd } = props
  const [errMessage, setErrMessage] = useState('')
  const [name, setName] = useState('')
  const [size, setSize] = useState('')
  const [color, setColor] = useState('')
  const [finish, setFinish] = useState('')
  const [customizeProduct, setCustomizeProduct] = useState([])
  const classes = useStyles()
  const availableName = [
    'Aero bikes',
    'Ghost bikes',
    'Raaw bikes',
    'Rocky bikes'
  ]
  const availableSize = ['Small', 'Medium', 'Large']
  const availableColor = ['Black', 'White', 'blue', 'Yellow', 'Red']
  const availableFinish = ['Polish', 'Matte']
  const handleSubmit = () => {
    //get bike and display the information add to cart
    if (name === '' || size === '' || color === '' || finish === '') {
      setErrMessage('All field is required')
      setTimeout(() => {
        setErrMessage('')
      }, 3000)
      return
    }
    request
      .post(BuildPath('/customer/getCustomize'))
      .set('Authorization', localStorage.getItem('Authorization'))
      .set('Accept', 'application/json')
      .send({
        name: name,
        color: color,
        size: size,
        finish: finish
      })
      .then(res => {
        console.log(res.body)
        if (res.status === 200) {
          setCustomizeProduct(res.body)
        }
      })
      .catch(err => {
        console.log(err)
      })
  }
  const handleClose = () => {
    setOpenCustomize(false)
    setCustomizeProduct([])
    setName('')
    setColor('')
    setSize('')
    setFinish('')
  }
  return (
    <Dialog
      open={openCustomize}
      onClose={handleClose}
      classes={classes.dialogWrapper}
      fullScreen
    >
      <AppBar>
        <Toolbar>
          <Typography variant='h6' className={classes.title}>
            Customize Your bike
          </Typography>
        </Toolbar>
      </AppBar>
      <DialogTitle id='form-dialog-title'>Product From</DialogTitle>
      <DialogContent>
        <Grid item xs={12}>
          <div style={{ color: 'red' }}>{errMessage}</div>
        </Grid>
        <FormControl variant='outlined' fullWidth margin='dense'>
          <InputLabel>Name</InputLabel>
          <Select onChange={e => setName(e.target.value)} label='Type'>
            {availableName.map(row => {
              return <MenuItem value={`${row}`}>{row}</MenuItem>
            })}
          </Select>
        </FormControl>
        <FormControl variant='outlined' fullWidth margin='dense'>
          <InputLabel>Color</InputLabel>
          <Select onChange={e => setColor(e.target.value)} label='Color'>
            {availableColor.map(row => {
              return <MenuItem value={`${row}`}>{row}</MenuItem>
            })}
          </Select>
        </FormControl>
        <FormControl variant='outlined' fullWidth margin='dense'>
          <InputLabel>Size</InputLabel>
          <Select onChange={e => setSize(e.target.value)} label='Type'>
            {availableSize.map(row => {
              return <MenuItem value={`${row}`}>{row}</MenuItem>
            })}
          </Select>
        </FormControl>
        <FormControl variant='outlined' fullWidth margin='dense'>
          <InputLabel>Finish</InputLabel>
          <Select onChange={e => setFinish(e.target.value)} label='Finish'>
            {availableFinish.map(row => {
              return <MenuItem value={`${row}`}>{row}</MenuItem>
            })}
          </Select>
        </FormControl>
        <Grid container className={classes.wrapper} spacing={2}>
          {customizeProduct.map(row => {
            return <Product product={row} handleAdd={handleAdd}></Product>
          })}
        </Grid>
      </DialogContent>
      <DialogActions classes={{ root: classes.leftDialogActions }}>
        <Button variant='outlined' color='secondary' onClick={handleClose}>
          Close
        </Button>
        <Button variant='outlined' color='primary' onClick={handleSubmit}>
          Filter
        </Button>
      </DialogActions>
    </Dialog>
  )
}
export { Customize }
