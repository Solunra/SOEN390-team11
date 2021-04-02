import React from 'react'
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

  return (
    <MuiThemeProvider theme={theme}>
      <div className={classes.root}>
        <List style={flexContainer}>
          <ListItem>
            <AddressCard
              fullname='John Doe'
              address='1455 Boulevard de Maisonneuve'
              city='Montreal'
              state='QC'
              country='Canada'
              zip='H3G'
            />
          </ListItem>
          <ListItem>
            <AddressCard
              fullname='John Doe'
              address='1455 Boulevard de Maisonneuve'
              city='Montreal'
              country='Canada'
              zip='H3G'
            />
          </ListItem>
          <ListItem>
            <AddressCard
              fullname='John Doe'
              address='1455 Boulevard de Maisonneuve'
              city='Montreal'
              state='QC'
              country='Canada'
              zip='H3G'
            />
          </ListItem>
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
