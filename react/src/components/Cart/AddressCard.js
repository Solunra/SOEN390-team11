import React from 'react'
import { makeStyles } from '@material-ui/core/styles'
import Card from '@material-ui/core/Card'
import CardActions from '@material-ui/core/CardActions'
import CardContent from '@material-ui/core/CardContent'
import Radio from '@material-ui/core/Radio'
import Button from '@material-ui/core/Button'
import Typography from '@material-ui/core/Typography'

import ShippingForm from './ShippingForm'

const useStyles = makeStyles({
  root: {
    minWidth: 275,
    textAlign: 'center',
    '&:hover': {
      background: '#eee',
      cursor: 'pointer'
    }
  },
  title: {
    fontSize: 14
  },
  pos: {
    marginBottom: 12
  },
  radio: {
    width: 'auto'
  }
})

export default function AddressCard ({
  address,
  selected,
  setSelected,
  setShipping
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

  return (
    <Card
      className={classes.root}
      variant='outlined'
      onClick={() => setSelected(customerID)}
    >
      <Radio
        className={classes.radio}
        checked={selected === customerID}
        value={customerID}
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
      <CardActions>
        <Button
          variant='outlined'
          color='secondary'
          size='small'
          onClick={() => setOpen(true)}
        >
          Edit
        </Button>
      </CardActions>
      <ShippingForm
        open={open}
        setOpen={setOpen}
        setShipping={setShipping}
        address={address}
      />
    </Card>
  )
}
