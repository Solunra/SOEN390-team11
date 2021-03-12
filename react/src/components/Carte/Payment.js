import React, {useState} from 'react';
import Typography from '@material-ui/core/Typography';
import Grid from '@material-ui/core/Grid';
import TextField from '@material-ui/core/TextField';

const Payment=(props)=> {
    const {setPayment} = props
    const [error,setError]=useState('');
    const cardNumberLength=16;
    const closeError=()=>{
        setTimeout(()=>{
            setError("")
        }, 5000);
    }
    const validation =(e , type)=>{
        let value =e.target.value;
        if(value.trim() ===""){
            return;
        }
        switch (type) {
            case 0:
                    setPayment(prev => new Map([...prev, ['name', e.target.value]]))
                return;
            case 1:
                if(!isNaN(value)){
                    setPayment(prev => new Map([...prev, ['cardnumber', e.target.value]]))
                }
                else{
                    setError("Card Number have to be number");
                    closeError();
                }
                return;
            case 2:
                if(!isNaN(value)){
                    if(value.length===4) {
                        const month = Math.trunc(value / 100);
                        const year = value % 100;
                        const curyear = new Date().toLocaleDateString('en', {year: '2-digit'});
                        const curmonth = new Date().toLocaleDateString('en', {month: '2-digit'});
                        if (month > 0 && month < 13 && month > curmonth && year >= curyear) {
                            setPayment(prev => new Map([...prev, ['exp', e.target.value]]))
                        }
                    }
                }
                else{
                    setError("Invalid Expired date");
                    closeError();
                }
                return;
            case 3:
                if(!isNaN(value)){
                    setPayment(prev => new Map([...prev, ['cvv', e.target.value]]))
                }
                else{
                    setError("CVV has to be number");
                    closeError();
                }
                return;
    }
    };

    return (
        <>
            <Typography variant="h6" gutterBottom>
                Payment method
            </Typography>
            <Grid container spacing={3}>
                <Grid item xs={12}>
                    <div style={ {color: 'red' }}>{error}</div>
                </Grid>
                <Grid item xs={12} md={6}>
                    <TextField
                        required id="cardName"
                        label="Name on card"
                        fullWidth
                        onChange={(e)=>{validation(e,0)}}
                    />
                </Grid>
                <Grid item xs={12} md={6}>
                    <TextField
                        required
                        id="cardNumber"
                        label="Card number"
                        fullWidth
                        onChange={(e)=>{validation(e,1)}}
                        InputProps={{
                            inputProps: {
                                maxLength: 16
                            }
                        }}
                    />
                </Grid>
                <Grid item xs={12} md={6}>
                    <TextField
                        required id="expDate"
                        label="Expiry date"
                        fullWidth
                        InputProps={{
                            inputProps: {
                                maxLength: 4
                            }
                        }}
                        onChange={(e)=>{validation(e,2)}}
                    />
                </Grid>
                <Grid item xs={12} md={6}>
                    <TextField
                        required
                        id="cvv"
                        label="CVV"
                        helperText="Last three digits on signature strip"
                        fullWidth
                        InputProps={{
                            inputProps: {
                                maxLength: 3
                            }
                        }}
                        onChange={(e)=>{validation(e,3)}}
                    />
                </Grid>
            </Grid>
        </>
    );
}
export {Payment}