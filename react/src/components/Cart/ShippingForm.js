import Grid from '@material-ui/core/Grid';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import {makeStyles} from '@material-ui/core/styles';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogTitle from '@material-ui/core/DialogTitle';
import request from 'superagent';
import BuildPath from '../RequestBuilder';

const useStyles = makeStyles (theme => ({
  root: {
    '& > *': {
      margin: theme.spacing (1),
    },
  },
}));

const ShippingForm = ({open, setOpen, address, updateAddressList}) => {
  const classes = useStyles ();

  const createAddress = () => {
    address['userID'] = localStorage.getItem ('userId');
    request
      .put(BuildPath('/customer/address'))
      .set('Authorization', localStorage.getItem('Authorization'))
      .send(address)
      .set('Accept', 'application/json')
      .catch(err => {
          console.log(err.rawResponse)
        console.error(err)
      })
    updateAddressList();
  }

  const updateAddress = () => {
    address['userID'] = localStorage.getItem ('userId');
    request
      .post(BuildPath('/customer/address'))
      .set('Authorization', localStorage.getItem('Authorization'))
      .send(address)
      .set('Accept', 'application/json')
      .catch(err => {
          console.log(err.rawResponse)
        console.error(err)
      })
      updateAddressList();
  };

  address = address || {
    firstname: '',
    lastname: '',
    address: '',
    city: '',
    province: '',
    zip: '',
    country: '',
  };

  const handleClose = () => {
    setOpen (false);
  };

  return (
    <div className={classes.root}>
      <Dialog
        open={open}
        onClose={handleClose}
        aria-labelledby="form-dialog-title"
      >
        <DialogTitle id="form-dialog-title">
          {address.hasOwnProperty ('customerID') ? 'Edit' : 'New'} Address
        </DialogTitle>
        <DialogContent>
          <Grid
            container
            spacing={3}
            justify="space-around"
            style={{
              margin: 0,
              width: '100%',
            }}
          >
            {Object.keys (address).map (
              key =>
                key.includes ('ID') === false &&
                <Grid item xs={12} sm={key === 'address' ? 12 : 3} key={key}>
                  <TextField
                    required
                    id={key}
                    name={key}
                    label={key}
                    defaultValue={address[key]}
                    style={{
                      textTransform: 'capitalize',
                    }}
                    fullWidth
                    onChange={e => (address[key] = e.target.value)}
                  />
                </Grid>
            )}
            <Grid item xs={12} />
          </Grid>
        </DialogContent>
        <DialogActions style={{justifyContent: 'center'}}>
          <Button onClick={handleClose} color="default">
            Cancel
          </Button>
          <Button
            onClick={() => {
              handleClose ();

              if (address.hasOwnProperty ('customerID')) updateAddress ();
              else createAddress ();

              setTimeout (updateAddressList, 150);
            }}
            color="primary"
          >
            Save
          </Button>
        </DialogActions>
      </Dialog>
    </div>
  );
};

export default ShippingForm;
