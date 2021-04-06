import React, {useEffect, useState} from 'react';
import {
    Dialog,
    DialogTitle,
    DialogContent,
    makeStyles,
    DialogContentText,
    DialogActions,
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

const EditForm = (props) =>{
    const {open, setOpen, userEditInfo,loading,setLoading} = props;
    const [error, setError] = useState('');
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const classes = useStyles();

    const closeError=()=>{
        setTimeout(()=>{
            setError("")
        }, 5000);
    }
    const handleSubmit = (e)=>{
        if(checkValue()){
            request
                .post(BuildPath("/account/edit/"))
                .set('Authorization', localStorage.getItem("Authorization"))
                .send(
                    {
                        "username": !username?userEditInfo['username']:username,
                        "password":!password?userEditInfo['password']:password ,
                        "userID":userEditInfo['userID']
                    }
                )
                .set('Accept', 'application/json')
                .then(res => {
                    if(res.status === 200 ){
                        cleanUp();
                        setOpen(false);
                        setLoading(!loading);
                    }
                })
                .catch(err =>{
                    setError("Cannot Create account");
                    setTimeout(()=>{
                        setError("")
                    }, 5000);
                });
        }
        else{
            setError("All value is not changing, old value");
            setTimeout(()=>{
                setError("")
            }, 5000);
        }
    };

    const checkValue=()=>{

            if(username==='' && password ===''){
                return false;
            }
            else{
                if(username!=='' || password !==''){
                    return true;
                }
            }

    };
    const cleanUp=()=>{
        setUsername('');
        setPassword('');
    }

    const validation =(e , type)=>{
        let value =e.target.value;
        if(value.trim() ===""){
            return;
        }
        switch (type) {
            case 1:
                setUsername(value);
                return;
            case 2:
                if(value.length >8){
                    let capital = /[A-Z]/;
                    let specialChar = /[*$%#]/;
                    let digit = /[0-9]/;
                    if(capital.test(value)&&specialChar.test(value)&&digit.test(value)){
                        setPassword(value);
                    }
                }
                return;
        }
    };


    return (
        <Dialog open={open} onClose={()=>{setOpen(false)}} aria-labelledby="form-dialog-title" classes={ classes.dialogWrapper }>
            <DialogTitle id="form-dialog-title">User From</DialogTitle>
            <DialogContent >
                <DialogContentText>Edit Information</DialogContentText>
                <Grid item xs={12}>
                    <div style={ {color: 'red' }}>{error}</div>
                </Grid>

                <TextField
                    autoFocus
                    margin="dense"
                    defaultValue={userEditInfo['email']}
                    label="Email"
                    fullWidth
                    variant="outlined"
                    disabled
                />
                <TextField
                    autoFocus
                    margin="dense"
                    defaultValue={userEditInfo['username']}
                    onChange={e =>{validation(e,1)}}
                    label="UserName"
                    fullWidth
                    variant="outlined"
                />
                <TextField
                    autoFocus
                    margin="dense"
                    defaultValue={userEditInfo['password']}
                    onChange={e =>{validation(e,2)}}
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
export {EditForm};