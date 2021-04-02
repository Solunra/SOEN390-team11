import Grid from '@material-ui/core/Grid'
import Button from '@material-ui/core/Button'
import TextField from '@material-ui/core/TextField'
import { makeStyles } from '@material-ui/core/styles'

import Dialog from '@material-ui/core/Dialog'
import DialogActions from '@material-ui/core/DialogActions'
import DialogContent from '@material-ui/core/DialogContent'
import DialogTitle from '@material-ui/core/DialogTitle'

const useStyles = makeStyles(theme => ({
  root: {
    '& > *': {
      margin: theme.spacing(1)
    }
  }
}))

const ShippingForm = ({ open, setOpen, setShipping }) => {
  const classes = useStyles()

  const handleClose = () => {
    setOpen(false)
  }

  return (
    <div className={classes.root}>
      <Dialog
        fullScreen
        open={open}
        onClose={handleClose}
        aria-labelledby='form-dialog-title'
      >
        <DialogTitle id='form-dialog-title'>New Address</DialogTitle>
        <DialogContent>
          <Grid
            container
            spacing={3}
            justify='space-around'
            style={{
              margin: 0,
              width: '100%'
            }}
          >
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
                  setShipping(
                    prev => new Map([...prev, ['address', e.target.value]])
                  )
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
                  setShipping(
                    prev => new Map([...prev, ['city', e.target.value]])
                  )
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
                  setShipping(
                    prev => new Map([...prev, ['zip', e.target.value]])
                  )
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
                  setShipping(
                    prev => new Map([...prev, ['country', e.target.value]])
                  )
                }
              />
            </Grid>
            <Grid item xs={12}></Grid>
          </Grid>
        </DialogContent>
        <DialogActions style={{ display: 'inline-block' }}>
          <Button onClick={handleClose} color='default'>
            Cancel
          </Button>
          <Button onClick={handleClose} color='primary'>
            Create
          </Button>
        </DialogActions>
      </Dialog>
    </div>
  )
}

export default ShippingForm
