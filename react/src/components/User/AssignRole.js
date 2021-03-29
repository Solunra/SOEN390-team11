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

const AssignRole = (props) =>{

    const {open, setOpen, setUserInfo,userInfo,loading,setLoading} = props;
    const [error, setError] = useState('');
    const [role, setRole] = useState('');
    const classes = useStyles();
    const roleList=["CUSTOMER", "ADMIN"];
    const closeError=()=>{
        setTimeout(()=>{
            setError("")
        }, 5000);
    }
    const handleSubmit = (e)=>{
    //    edit to assign new role;
        if(!role || role ==="None"){
            //close
            cleanUp();
            setOpen(false);
            return;
        }
        request
            .post(BuildPath("/account/edit/"))
            .set('Authorization', localStorage.getItem("Authorization"))
            .send(
                {
                    "username": userInfo['username'],
                    "password":userInfo['password'] ,
                    "email":userInfo['email'],
                    "role": role,
                    "userID":userInfo['userID']
                }
            )
            .set('Accept', 'application/json')
            .then(res => {
                console.log(res);
                cleanUp();
                setOpen(false);
                setRole('');
                setLoading(!loading);

            })
            .catch(err =>{
                setError("Cannot Assign Role");
                setTimeout(()=>{
                    setError("")
                }, 5000);
            });

    };

    const cleanUp=()=>{
        setRole('');
        setUserInfo({});
    }


    return (
        <Dialog open={open} onClose={()=>{setOpen(false)}} aria-labelledby="form-dialog-title" classes={ classes.dialogWrapper }>
            <DialogTitle id="form-dialog-title">Assign Role From</DialogTitle>
            <DialogContent >
                <DialogContentText>Assign Role</DialogContentText>
                <Grid item xs={12}>
                    <div style={ {color: 'red' }}>{error}</div>
                </Grid>

                <TextField
                    autoFocus
                    margin="dense"
                    defaultValue={userInfo['email']}
                    disabled
                    label="Email"
                    fullWidth
                    variant="outlined"
                />
                <TextField
                    autoFocus
                    margin="dense"
                    defaultValue={userInfo['username']}
                    disabled
                    label="UserName"
                    fullWidth
                    variant="outlined"
                />
                <FormControl variant="outlined" fullWidth >
                    <InputLabel>Role</InputLabel>
                    <Select
                        // value={`${vendorID===''?userEditInfo['vendorID']:vendorID}`}
                        value={`${role===''?userInfo['role']:role}`}
                        onChange={e =>{setRole(e.target.value)}}
                        label="Vendor"
                    >
                        <MenuItem value={`None`}>Not Assign</MenuItem>
                        {roleList.map((row) =>{
                            return <MenuItem value={row} >{row}</MenuItem>;
                        })}
                    </Select>
                </FormControl>
                <TextField
                    autoFocus
                    margin="dense"
                    defaultValue={userInfo['password']}
                    disabled
                    label="Password"
                    type = "password"
                    fullWidth
                    variant="outlined"
                />
            </DialogContent>
            <DialogActions classes={{ root: classes.leftDialogActions }}>
                <Button onClick={()=>{setOpen(false)}} color="primary">Cancel</Button>
                <Button onClick={handleSubmit} color="primary">Submit</Button>
            </DialogActions>
        </Dialog>
    )
};
export {AssignRole};