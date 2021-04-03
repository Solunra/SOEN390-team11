import React from 'react'
import { makeStyles } from '@material-ui/core/styles'
import Card from '@material-ui/core/Card'
import CardActions from '@material-ui/core/CardActions'
import CardContent from '@material-ui/core/CardContent'
import Button from '@material-ui/core/Button'
import Typography from '@material-ui/core/Typography'

import ShippingForm from './ShippingForm'

const useStyles = makeStyles({
  root: {
    minWidth: 275
  },
  bullet: {
    display: 'inline-block',
    margin: '0 2px',
    transform: 'scale(0.8)'
  },
  title: {
    fontSize: 14
  },
  pos: {
    marginBottom: 12
  }
})

export default function AddressCard ({
  fullname,
  address,
  city,
  state,
  country,
  zip,
  setShipping
}) {
  const classes = useStyles()
  const [open, setOpen] = React.useState(false)

  React.useEffect(() => {
    console.log(open)
  }, [open])

  return (
    <Card className={classes.root} variant='outlined'>
      <CardContent>
        <Typography
          className={classes.title}
          color='textSecondary'
          gutterBottom
        >
          Customer ID Here?
        </Typography>
        <Typography variant='h5' component='h2'>
          {fullname}
        </Typography>
        <Typography className={classes.pos} color='textSecondary'>
          {/* some side notes? */}
        </Typography>
        <Typography variant='body2' component='p'>
          {address}
          <br />
          {[city, state].filter(e => e).join(',')}
          <br />
          {country}
          <br />
          {zip}
        </Typography>
      </CardContent>
      <CardActions>
        <Button variant='outlined' size='small' onClick={() => setOpen(true)}>
          Edit
        </Button>
      </CardActions>
      <ShippingForm open={open} setOpen={setOpen} setShipping={setShipping} />
    </Card>
  )
}
