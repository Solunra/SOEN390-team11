import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Grid from '@material-ui/core/Grid';
import Paper from '@material-ui/core/Paper';
import Typography from '@material-ui/core/Typography';
import bike from "../Utils/bike.png";
import {RemoveCircleOutlineRounded} from "@material-ui/icons";
import AddCircleRoundedIcon from "@material-ui/icons/AddCircleRounded";
import Button from "@material-ui/core/Button";

const useStyles = makeStyles((theme) => ({
    root: {
        flexGrow: 1,
    },
    paper: {
        padding: theme.spacing(2),
        margin: 'auto',
        maxWidth: 500,
    },
    image: {
        width: 128,
        height: 128,
    },
    img: {
        margin: 'auto',
        display: 'block',
        maxWidth: '100%',
        maxHeight: '100%',
    },
    container: {
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        gap: '20px'
    }
}));

const CartItem = (props)=> {
    const classes = useStyles();
    const {item, handleIncrement,handleRemove} = props;

    return (
        <div className={classes.root}>
            <Paper className={classes.paper}>
                <Grid container spacing={2}>
                    <Grid item>
                        <div className={classes.image}>
                            <img className={classes.img} alt="complex" src={bike} />
                        </div>
                    </Grid>
                    <Grid item xs={12} sm container>
                        <Grid item xs container direction="column" spacing={2}>
                            <Grid item xs>
                                <Typography gutterBottom variant="subtitle1">
                                    {item['product']['name']}
                                </Typography>
                                <Typography variant="body2" gutterBottom>
                                    <span>{item['product']['color']}</span>
                                </Typography>
                                <Typography variant="body2" color="textSecondary">
                                    <div className={classes.container}>
                                        <RemoveCircleOutlineRounded onClick={()=>{handleRemove(item, "one")}}/>
                                        {item['count']}
                                        <AddCircleRoundedIcon onClick={()=>{handleIncrement(item)}}/>
                                    </div>
                                </Typography>
                            </Grid>
                            <Grid item>
                                <Typography variant="body2" style={{ cursor: 'pointer' }}>
                                    <Button onClick={()=>{handleRemove(item, "all")}}>Remove</Button>
                                </Typography>
                            </Grid>
                        </Grid>
                        <Grid item>
                            <Typography variant="subtitle1">$19.00</Typography>
                        </Grid>
                    </Grid>
                </Grid>
            </Paper>
        </div>
    );
}
export {CartItem};