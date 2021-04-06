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

export default function AddressCard ({
  address,
  selected,
  Select,
  updateAddressList
}) {
  const classes = useStyles()
  const [open, setOpen] = React.useState(false)
  const {
    customerID,
    firstname,
    lastname,
    address: addr,
    city,
    province,
    country,
    zip
  } = address
  const deleteAddress = () => {
    request
      .delete(BuildPath('/customer/address/' + customerID))
      .set('Authorization', localStorage.getItem('Authorization'))
      .accept('application/json')
      .catch(err => {
        console.error(err)
      })
  }

  return (
    <Card
      className={classes.root}
      variant='outlined'
      onClick={() => Select(customerID, address)}
    >
      <div className={classes.content}>
        <Radio
          className={classes.radio}
          checked={selected === customerID}
          value={customerID}
          color='default'
          name='radio-button'
          inputProps={{ 'aria-label': `address #${customerID}` }}
        />
        <CardContent>
          <Typography
            className={classes.title}
            color='textSecondary'
            gutterBottom
          >
            {/* Customer ID Here? */}
          </Typography>
          <Typography variant='h5' component='h2'>
            {[firstname, lastname].filter(e => e).join(' ')}
          </Typography>
          <Typography className={classes.pos} color='textSecondary'>
            {/* some side notes? */}
          </Typography>
          <Typography variant='body2' component='p'>
            {addr}
            <br />
            {[city, province].filter(e => e).join(',')}
            <br />
            {country}
            <br />
            {zip}
          </Typography>
        </CardContent>
      </div>
      <CardActions style={{ justifyContent: 'center' }}>
        <Button variant='outlined' onClick={() => setOpen(true)}>
          <EditLocationTwoTone size='small' style={{ color: 'darkblue' }} />
        </Button>
        <Button
          variant='outlined'
          onClick={() => {
            deleteAddress()
            setTimeout(updateAddressList, 150)
          }}
        >
          <DeleteForeverTwoTone size='small' style={{ color: 'darkred' }} />
        </Button>
      </CardActions>
      <ShippingForm open={open} setOpen={setOpen} address={address} updateAddressList={updateAddressList}/>
    </Card>
  )
}
