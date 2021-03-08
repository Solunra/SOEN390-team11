import React, {useState} from 'react';
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
    }
}))

const RawMaterialForm = (props) =>{

    const {open, handleClose,setRowData,rowData} = props;
    const [name, setName] = useState('');
    const [description, setDescription] = useState('');
    const [vendor, setVendor] = useState('');
    const [price, setPrice] = useState('');
    const [unit, setUnit] = useState('');
    const vendorlist = [
        {"vendorID":1,"type":"Vendor1","saleID":"saleid 1"},
        {"vendorID":2,"type":"Vendor2","saleID":"saleid 2"},
        {"vendorID":3,"type":"Vendor3","saleID":"saleid 3"},
        {"vendorID":4,"type":"Vendor4","saleID":"saleid 4"},
    ];
    console.log(`Vendor ${rowData['vendor']}`);
    const classes = useStyles();

    const clearValue =() => {
        // setType('');
        // setSaleID('');
    }

    const checkValue = ()=>{
        // if(){
        //     return false;
        // }
        // return true;
    }
    const handleCancel =() =>{
        // clearValue();
        // setRowData({});
        handleClose();
    }
    const cleanup=()=>{
        // clearValue();
        // handleClose();
        // setRowData({});
    }



    const handleSubmit = (e)=>{
        if(JSON.stringify(rowData) !== JSON.stringify({}))
        {
            editOperation();
        }
        else{
            addOperation();
        }

    }
    const addOperation =()=>{
        cleanup();
        // if(checkValue())
        // {
        //     request
        //         .post(BuildPath())
        //         .set('Authorization', localStorage.getItem("Authorization"))
        //         .set('Accept', 'application/json')
        //         .send(
        //             {
        //                 "type":type,
        //                 "saleID":saleID
        //             }
        //         )
        //         .then(res => {
        //             if (res.status === 201) {
        //                 // re render
        //                 cleanup();
        //             }
        //         })
        //         .catch(err => {
        //             console.log(err);
        //             cleanup();
        //         });
        // }
    }
    const editOperation =() => {
        request
            .put(BuildPath())
            // .set('Authorization', localStorage.getItem("Authorization"))
            // .set('Accept', 'application/json')
            // .send(
            //     {
            //         "type":!type?rowData['type']:type,
            //         "saleID":!saleID?rowData['saleID']:saleID
            //     }
            // )
            // .then(res => {
            //     console.log(res.body);
            //     if (res.status === 200) {
            //         cleanup();
            //     }
            // })
            // .catch(err => {
            //     cleanup();
            //     // setErrMessage(err.response.body['message']);
            // });


    }
    return (
        <Dialog open={open} onClose={handleClose} aria-labelledby="form-dialog-title" classes={ classes.dialogWrapper }>
            <DialogTitle id="form-dialog-title">Product From</DialogTitle>
            <DialogContent>
                <DialogContentText> Create or Edit Raw Material</DialogContentText>
                <TextField
                    autoFocus
                    margin="dense"
                    defaultValue={rowData['name']}
                    onChange = {e => setName(e.target.value)}
                    label="type"
                    fullWidth
                    variant="outlined"
                />
                <TextField
                    autoFocus
                    margin="dense"
                    defaultValue={rowData['description']}
                    onChange = {e => setDescription(e.target.value)}
                    label="Sale ID"
                    fullWidth
                    variant="outlined"
                />
                <FormControl variant="outlined" fullWidth >
                    <InputLabel>Vendor</InputLabel>
                    <Select
                        value={`Vendor ${rowData['vendor']}`}
                        onChange={e => {alert(e.target.value)}}
                        label="Vendor"
                    >
                        <MenuItem value={`None`}>None</MenuItem>
                        {vendorlist.map((row) =>{
                            return <MenuItem value={`Vendor ${row.vendorID}`} >Vendor {row.vendorID}</MenuItem>;
                        })}
                    </Select>
                </FormControl>

                <TextField
                    autoFocus
                    margin="dense"
                    defaultValue={rowData['price']}
                    onChange = {e => setPrice(e.target.value)}
                    label="price"
                    variant="outlined"
                />

                <TextField
                    autoFocus
                    margin="dense"
                    defaultValue={rowData['unit']}
                    onChange = {e => setUnit(e.target.value)}
                    label="unit"
                    variant="outlined"
                />

            </DialogContent>
            <DialogActions classes={{ root: classes.leftDialogActions }}>
                <Button onClick={handleCancel} color="primary">Cancel</Button>
                <Button onClick={handleSubmit} color="primary">Submit</Button>
            </DialogActions>
        </Dialog>
    )
}
export {RawMaterialForm};