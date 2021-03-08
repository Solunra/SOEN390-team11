import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import { Alert, AlertTitle } from '@material-ui/lab';
import CloseIcon from '@material-ui/icons/Close';

const useStyles = makeStyles((theme) => ({
    alertRoot: {
        width: '100%',
        '& > * + *': {
            marginTop: theme.spacing(2),
        },
    },
}));

const AlertErr=(props) => {
    const {message, closeAlert} = props;
    const classes = useStyles();

    return (
        <div className={classes.alertRoot}>
            <Alert severity="error">
                <AlertTitle>Error</AlertTitle>
                <strong>{message}</strong>
                <CloseIcon onClick={closeAlert}/>
            </Alert>
        </div>
    );
}
export {AlertErr};
