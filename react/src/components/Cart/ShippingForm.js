import React from 'react'
import { makeStyles } from '@material-ui/core/styles'
import { MuiThemeProvider, createMuiTheme } from '@material-ui/core/styles'
import Grid from '@material-ui/core/Grid'
import TextField from '@material-ui/core/TextField'
import List from '@material-ui/core/List'
import ListItem from '@material-ui/core/ListItem'
import Button from '@material-ui/core/Button'
import AddressCard from './AddressCard'

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

const ShippingForm = ({ setShipping }) => {
  const classes = useStyles()

  const inputForm = (
    <Grid container spacing={3}>
      <Grid item xs={12} sm={6}>
        <TextField
          required
          id='firstName'
          name='firstName'
          label='First name'
          fullWidth
          onChange={e =>
            setShipping(
              prev => new Map([...prev, ['firstname', e.target.value]])
            )
          }
        />
      </Grid>
      <Grid item xs={12} sm={6}>
        <TextField
          required
          id='lastName'
          name='lastName'
          label='Last name'
          fullWidth
          onChange={e =>
            setShipping(
              prev => new Map([...prev, ['lastname', e.target.value]])
            )
          }
        />
      </Grid>
      <Grid item xs={12}>
        <TextField
          required
          id='address1'
          name='address1'
          label='Address line 1'
          fullWidth
          onChange={e =>
            setShipping(prev => new Map([...prev, ['address', e.target.value]]))
          }
        />
      </Grid>
      <Grid item xs={12} sm={6}>
        <TextField
          required
          id='city'
          name='city'
          label='City'
          fullWidth
          onChange={e =>
            setShipping(prev => new Map([...prev, ['city', e.target.value]]))
          }
        />
      </Grid>
      <Grid item xs={12} sm={6}>
        <TextField
          id='state'
          name='state'
          label='State/Province/Region'
          fullWidth
          onChange={e =>
            setShipping(
              prev => new Map([...prev, ['province', e.target.value]])
            )
          }
        />
      </Grid>
      <Grid item xs={12} sm={6}>
        <TextField
          required
          id='zip'
          name='zip'
          label='Zip / Postal code'
          fullWidth
          onChange={e =>
            setShipping(prev => new Map([...prev, ['zip', e.target.value]]))
          }
        />
      </Grid>
      <Grid item xs={12} sm={6}>
        <TextField
          required
          id='country'
          name='country'
          label='Country'
          fullWidth
          onChange={e =>
            setShipping(prev => new Map([...prev, ['country', e.target.value]]))
          }
        />
      </Grid>
    </Grid>
  )
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
            <Button variant='outlined'>Add New Address</Button>
          </ListItem>
        </List>
      </div>
    </MuiThemeProvider>
  )
}
export default ShippingForm
