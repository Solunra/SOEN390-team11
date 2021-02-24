import React, {useState} from 'react';
import { Dialog, DialogTitle, DialogContent, makeStyles, DialogContentText,DialogActions } from '@material-ui/core';
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

const ProductForm = (props) =>{

    const {data,re_render, setRe_render ,open, handleClose,setData,setErr,setErrMessage} = props;
    const [name, setName] = useState('');
    const [type, setType] = useState('');
    const [size, setSize] = useState('');
    const [color, setColor] = useState('');
    const [finish, setFinish] = useState('');
    const [grade, setGrade] = useState('');
    const classes = useStyles();

    const clearValue =() => {
        setName('');
        setGrade('');
        setFinish('');
        setColor('');
        setType('');
        setSize('');
    }

    const checkValue = ()=>{
        if(!name|| !type|| !size || !color || !finish || !grade){
            return false;
        }
        return true;
    }
    const handleCancel =() =>{
        clearValue();
        setData({});
        handleClose();
    }


    const handleSubmit = (e)=>{
        if(JSON.stringify(data) !== JSON.stringify({}))
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
                .post(BuildPath("/product/create"))
                .set('Authorization', localStorage.getItem("Authorization"))
                .set('Accept', 'application/json')
                .send(
                    {
                        "name":name,
                        "type":type,
                        "color":color,
                        "size":size,
                        "finish":finish,
                        "grade":grade
                    }
                )
                .then(res => {
                    if (res.status === 201) {
                        setRe_render(!re_render);
                        clearValue();
                        handleClose();

                    }
                })
                .catch(err => {
                    console.log(err);
                });
        }
    }
    const editOperation =() => {
                request
                .put(BuildPath("/product/update/"+data['productid']))
                .set('Authorization', localStorage.getItem("Authorization"))
                .set('Accept', 'application/json')
                .send(
                    {
                        "name":!name?data['name']:name,
                        "type":!type?data['type']:type,
                        "color":!color?data['color']:color,
                        "size":!size?data['size']:size,
                        "finish":!finish?data['finish']:finish,
                        "grade":!grade?data['grade']:grade
                    }
                )
                .then(res => {
                    console.log(res.body);
                    if (res.status === 200) {
                        setRe_render(!re_render);
                        clearValue();
                        setData({});
                        handleClose();
                    }
                })
                .catch(err => {
                    clearValue();
                    setData({});
                    handleClose();
                    setErrMessage(err.response.body['message']);
                });


    }
    return (
        <Dialog open={open} onClose={handleClose} aria-labelledby="form-dialog-title" classes={ classes.dialogWrapper }>
            <DialogTitle id="form-dialog-title">Product From</DialogTitle>
            <DialogContent>
                <DialogContentText> Create or Edit product</DialogContentText>
                    <TextField
                        autoFocus
                        margin="dense"
                        defaultValue={data['name']}
                        onChange = {e => setName(e.target.value)}
                        label="Product name"
                        fullWidth
                        variant="outlined"
                    />
                    <TextField
                        autoFocus
                        margin="dense"
                        defaultValue={data['type']}
                        onChange = {e => setType(e.target.value)}
                        label="type"
                        fullWidth
                        variant="outlined"
                    />
                    <TextField
                        autoFocus
                        margin="dense"
                        defaultValue={data['size']}
                        onChange = {e => setSize(e.target.value)}
                        label="Frame Size"
                        fullWidth
                        variant="outlined"
                    />
                    <TextField
                        autoFocus
                        margin="dense"
                        defaultValue={data['color']}
                        onChange = {e => setColor(e.target.value)}
                        label="Color"
                        fullWidth
                        variant="outlined"
                    />
                    <TextField
                        autoFocus
                        margin="dense"
                        defaultValue={data['finish']}
                        onChange = {e => setFinish(e.target.value)}
                        label="Finish"
                        fullWidth
                        variant="outlined"
                    />
                    <TextField
                        autoFocus
                        margin="dense"
                        defaultValue={data['grade']}
                        onChange = {e => setGrade(e.target.value)
                        }
                        label="Grade"
                        fullWidth
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
export {ProductForm};