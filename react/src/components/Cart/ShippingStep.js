import React from 'react'
import request from 'superagent'
import BuildPath from '../RequestBuilder'
import {
  makeStyles,
  MuiThemeProvider,
  createMuiTheme
} from '@material-ui/core/styles'
import List from '@material-ui/core/List'
import ListItem from '@material-ui/core/ListItem'
import Button from '@material-ui/core/Button'
import AddressCard from './AddressCard'
import ShippingForm from './ShippingForm'

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
  flexDirection: 'row'
}

const ShippingStep = ({ setShipping }) => {
  const classes = useStyles()
  const [open, setOpen] = React.useState(false)
  const [selected, setSelected] = React.useState(null)
  const [addresses, setAddresses] = React.useState([])

  React.useEffect(() => {
    if (open === false)
      request
        .get(BuildPath('/account/customers'))
        .set('Authorization', localStorage.getItem('Authorization'))
        .set('Accept', 'application/json')
        .then(res => {
          if (res.status === 200) {
            setAddresses(
              res.body.sort((a, b) => {
                return a['firstname'] < b['firstname']
              })
            )
          }
        })
        .catch(err => {
          console.error(err)
        })
  }, [open])

  return (
    <MuiThemeProvider theme={theme}>
      <div className={classes.root}>
        <List style={flexContainer}>
          {addresses.map((address, i) => (
            <ListItem key={i}>
              <AddressCard
                address={address}
                selected={selected}
                setSelected={setSelected}
                setShipping={setShipping}
              />
            </ListItem>
          ))}
          <ListItem>
            <Button variant='outlined' onClick={() => setOpen(true)}>
              Add New Address
            </Button>
          </ListItem>
        </List>
      </div>
      <ShippingForm open={open} setOpen={setOpen} setShipping={setShipping} />
    </MuiThemeProvider>
  )
}
export default ShippingStep
