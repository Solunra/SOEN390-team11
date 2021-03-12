import React, {useState} from 'react';
import {
    Dialog,
    DialogTitle,
    DialogContent,
    makeStyles,
    DialogActions,
    AppBar, Toolbar, IconButton, InputLabel, Select, MenuItem, FormControl
} from '@material-ui/core';
import Button from '@material-ui/core/Button';
import Typography from "@material-ui/core/Typography";

const useStyles = makeStyles(theme => ({
    dialogWrapper: {
        width: '75%'
    },
    dialogAction: {
        justifyContent: 'flex-start'
    },
    leftDialogActions: {
        justifyContent: 'flex-start',
        gap: '20px'
    }
}))

const Customize = (props) =>{

    const {openCustomize,setOpenCustomize} = props;
    const [errMessage,setErrMessage]=useState('');
    const [invoiceId, setInvoiceId]= useState('');
    const [type,setType]=useState('');
    const [size, setSize]= useState('');
    const [color,setColor]=useState('');
    const [finish, setFinish]= useState('');
    const [orderList, setOrderList]= useState([]);
    const classes = useStyles();
    const availableType=["Mountain", "Road"];
    const availableSize=["Small","Medium","Large"];
    const availableColor=["Black","White","blue","Yellow","Red"];
    const availableFinish=["None","Polish","Matte"];
    const handleSubmit =()=>{
        //get bike and display the information add to cart
        // if(invoiceId ==='' || invoiceId.trim()===''){
        //     setErrMessage("All field is required");
        //     setTimeout(()=>{
        //         setErrMessage("")
        //     }, 3000);
        //     return;
        // }
        // request
        //     .get(BuildPath("/customer/purchase/"+invoiceId))
        //     .set('Authorization', localStorage.getItem("Authorization"))
        //     .set('Accept', 'application/json')
        //     .then(res => {
        //         if (res.status === 200) {
        //             setOrderList(res.body);
        //
        //             setInvoiceId("");
        //         }
        //     })
        //     .catch(err => {
        //         console.log(err);
        //         setInvoiceId("");
        //     });

    }
    const handleClose =()=>{
        setOpenCustomize(false);
        setInvoiceId('');
    }
    return (
        <Dialog open={openCustomize} onClose={handleClose}  classes={ classes.dialogWrapper } fullScreen>
            <AppBar >
                <Toolbar>
                    <Typography variant="h6" className={classes.title}>
                        Customize Your bike
                    </Typography>
                </Toolbar>
            </AppBar>
            <DialogTitle id="form-dialog-title">Product From</DialogTitle>
            <DialogContent>
                <div>
                    {errMessage ===""?"":errMessage}
                </div>
                <FormControl variant="outlined" fullWidth>
                    <InputLabel>Type</InputLabel>
                    <Select
                        onChange={e => setType(e.target.value)}
                        label="Type"
                    >
                        <MenuItem value={`None`}>None</MenuItem>
                        {availableType.map((row) =>{
                            return <MenuItem value={`${row}`} >{row}</MenuItem>;
                        })}
                    </Select>
                </FormControl>
                <FormControl variant="outlined" fullWidth>
                    <InputLabel>Color</InputLabel>
                    <Select
                        onChange={e => setColor(e.target.value)}
                        label="Color"
                    >
                        <MenuItem value={`None`}>None</MenuItem>
                        {availableColor.map((row) =>{
                            return <MenuItem value={`${row}`} >{row}</MenuItem>;
                        })}
                    </Select>
                </FormControl>
                <FormControl variant="outlined" fullWidth>
                    <InputLabel>Size</InputLabel>
                    <Select
                        onChange={e => setSize(e.target.value)}
                        label="Type"
                    >
                        <MenuItem value={`None`}>None</MenuItem>
                        {availableSize.map((row) =>{
                            return <MenuItem value={`${row}`} >{row}</MenuItem>;
                        })}
                    </Select>
                </FormControl>
                <FormControl variant="outlined" fullWidth>
                    <InputLabel>Finish</InputLabel>
                    <Select
                        onChange={e => setColor(e.target.value)}
                        label="Finish"
                    >
                        <MenuItem value={`None`}>None</MenuItem>
                        {availableFinish.map((row) =>{
                            return <MenuItem value={`${row}`} >{row}</MenuItem>;
                        })}
                    </Select>
                </FormControl>

            </DialogContent>
            <DialogActions classes={{ root: classes.leftDialogActions }}>
                <Button onClick={handleClose} color="primary">Close</Button>
                <Button onClick={handleSubmit} color="primary">Get Your Bike</Button>
            </DialogActions>
        </Dialog>
    )
}
export {Customize};