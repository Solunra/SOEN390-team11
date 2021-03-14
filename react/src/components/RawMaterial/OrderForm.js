import React, {useEffect, useState} from 'react';
import {
    Dialog,
    DialogTitle,
    DialogContent,
    makeStyles,
    DialogContentText,
    DialogActions,
    FormControl, InputLabel, Select, MenuList, Menu, MenuItem, Box
} from '@material-ui/core';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import request from 'superagent';
import BuildPath from '../RequestBuilder'


const useStyles = makeStyles(theme => ({
    dialogWrapper: {
        width: '75%'
    },
    dialogAction: {
        justifyContent: 'flex-start'
    },
    leftDialogActions: {
        justifyContent: 'flex-start'
    },
}))

const OrderForm= (props) =>{

    const {open, handleClose,setRowData,rowData,re_render, setRe_render,setErrMessage} = props;
    const [quantity, setQuantity] = useState('');
    // const [date, setDate] = useState(new Date());
    // const [time, setTime] = useState(new Time());
    const classes = useStyles();
    const getVendorList = () =>{
        // request
        //     .get(BuildPath("/vendor/"))
        //     .set('Authorization', localStorage.getItem("Authorization"))
        //     .set('Accept', 'application/json')
        //     .then(res => {
        //         if (res.status === 200)
        //         {
        //             if(JSON.stringify(vendorList) !== JSON.stringify(res.body)){
        //                 setVendorList(res.body);
        //             }
        //         }
        //     })
        //     .catch(err => {
        //         console.log(err);
        //     });
    };

    const checkValue = ()=>{

    }
    const handleCancel =() =>{
        clearValue();

    }
    const cleanup=()=>{
        clearValue();

    }

    const handleSubmit = (e)=>{
        // if(JSON.stringify(rowData) !== JSON.stringify({}))
        // {
        //     editOperation();
        // }
        // else{
        //     addOperation();
        // }

    }

    return (
        <Dialog open={open} onClose={handleClose} aria-labelledby="form-dialog-title" classes={ classes.dialogWrapper }>
            <DialogTitle id="form-dialog-title">Raw Material From</DialogTitle>
            <DialogContent >
                <DialogContentText> Order Raw Material</DialogContentText>

                <TextField
                    autoFocus
                    margin="dense"
                    defaultValue={rowData['name']}
                    //onChange = {e => setName(e.target.value)}
                    label="type"
                    fullWidth
                    variant="outlined"
                />
                <TextField
                    autoFocus
                    margin="dense"
                    defaultValue={rowData['description']}
                    onChange = {e => setDescription(e.target.value)}
                    label="Description"
                    multiline
                    rows={4}
                    fullWidth
                    variant="outlined"
                />
                <FormControl variant="outlined" fullWidth >
                    <InputLabel>Vendor</InputLabel>
                    <Select
                        value={rowData['vendorID']}
                        onChange={e => setVendorID(e.target.value)}
                        label="Vendor"
                    >
                        <MenuItem value={`None`}>None</MenuItem>
                        {vendorList.map((row) =>{
                            return <MenuItem value={`${row.vendorID}`} >{row.companyname}</MenuItem>;
                        })}
                    </Select>
                </FormControl>

                <TextField
                    type="number"
                    autoFocus
                    margin="dense"
                    defaultValue={rowData['price']}
                    onChange = {e => setPrice(e.target.value)}
                    label="price"
                    variant="outlined"
                />

                <FormControl variant="outlined" fullWidth >
                    <InputLabel>Unit</InputLabel>
                    <Select
                        value={rowData['unit']}
                        onChange={e => setUnit(e.target.value)}
                        label="Unit"
                    >
                        {unitList.map((row) =>{
                            return <MenuItem value={row} >{row}</MenuItem>;
                        })}
                    </Select>
                </FormControl>
            </DialogContent>
            <DialogActions classes={{ root: classes.leftDialogActions }}>
                <Button onClick={handleCancel} color="primary">Cancel</Button>
                <Button onClick={handleSubmit} color="primary">Submit</Button>
            </DialogActions>
        </Dialog>
    )
};
export {OrderForm};