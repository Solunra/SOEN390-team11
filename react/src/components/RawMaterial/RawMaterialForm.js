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
import { Grid} from "@material-ui/core";

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

const RawMaterialForm = (props) =>{

    const {open, handleClose,setRowData,rowData,re_render, setRe_render,setErrMessage} = props;
    const [name, setName] = useState('');
    const [description, setDescription] = useState('');
    const [vendorID, setVendorID] = useState('');
    const [vendorList, setVendorList] = useState([]);
    const [price, setPrice] = useState(0);
    const [unit, setUnit] = useState('');
    const unitList = ["None","Ton","L","kg","Piece"];
    const classes = useStyles();
    const getVendorList = () =>{
        request
            .get(BuildPath("/vendor/"))
            .set('Authorization', localStorage.getItem("Authorization"))
            .set('Accept', 'application/json')
            .then(res => {
                if (res.status === 200)
                {
                    if(JSON.stringify(vendorList) !== JSON.stringify(res.body)){
                        setVendorList(res.body);
                    }
                }
            })
            .catch(err => {
                console.log(err);
            });
    };
    useEffect(()=>{
        getVendorList();
    },[vendorList])
    const clearValue =() => {
        setUnit('');
        setVendorID('');
        setPrice(0);
        setDescription('');
        setName('');
    }

    const checkValue = ()=>{
        // console.log(!name +"\t"+ !description +"\t"+ price===0 +"\t"+ !price +"\t"+ unit ==="None"+"\t"+vendor==="None");
        if(!name || !description || price===0 || !price || unit ==="None"||vendorID === "None"){
            return false;
        }
        return true;
    }
    const handleCancel =() =>{
        clearValue();
        setRowData({});
        handleClose();
    }
    const cleanup=()=>{
        clearValue();
        handleClose();
        setRowData({});
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
        if(checkValue())
        {
            request
                .post(BuildPath("/rawmaterials/define"))
                .set('Authorization', localStorage.getItem("Authorization"))
                .set('Accept', 'application/json')
                .send(
                    {
                        "name":name,
                        "description":description,
                        "cost":price,
                        "unit":unit,
                        "vendorID":vendorID,
                    }
                )
                .then(res => {
                    if (res.status === 201) {
                        // re render
                        setRe_render(!re_render);
                        cleanup();
                    }
                })
                .catch(err => {
                    cleanup();
                    setErrMessage("cannot add");
                });
        }
    }
    const editOperation =() => {
        request
            ///edit/{rid}
            .put(BuildPath("/rawmaterials/edit/"+rowData['rawmaterialid']))
            .set('Authorization', localStorage.getItem("Authorization"))
            .set('Accept', 'application/json')
            .send(
                {
                    "name":!name?rowData['name']:name,
                    "description":!description?rowData['description']:description,
                    "price":!price?rowData['price']:price,
                    "unit":!unit?rowData['unit']:unit,
                    "vendorID":!vendorID?rowData['vendorID']:vendorID,
                }
            )
            .then(res => {
                if (res.status === 200) {
                    setRe_render(!re_render);
                    cleanup();
                }
            })
            .catch(err => {
                cleanup();
                setErrMessage("cannot edit");
            });
    }
    return (
        <Dialog open={open} onClose={handleClose} aria-labelledby="form-dialog-title" classes={ classes.dialogWrapper }>
            <DialogTitle id="form-dialog-title">Raw Material From</DialogTitle>
            <DialogContent >
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
export {RawMaterialForm};