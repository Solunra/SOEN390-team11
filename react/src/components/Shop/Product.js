import React from 'react';
import bike from "../Utils/bike.png";
import {Avatar, Card, CardActions, CardContent, CardHeader, CardMedia, makeStyles, Paper} from "@material-ui/core";
import Typography from "@material-ui/core/Typography";
import Button from "@material-ui/core/Button";
import Grid from "@material-ui/core/Grid";

const useStyles = makeStyles(theme => ({

    cardMedia: {
      height: 0,
      paddingTop: '56.25%', // 16:9,
      marginTop:'30'
    },
    button:{
        backgroundColor: '#66ccff'
    }


}))

const Product = props => {
    const {product, handleAdd} = props;
    const classes = useStyles();
    return (

            <Card className={classes.container}>
                <CardHeader
                    title={product['name']}
                    subheader={`100$`}
                />
                <CardMedia className={classes.cardMedia} image={bike} />
                <CardContent>
                    {/*list all the detail in small letter here */}
                    <Typography variant="body2" component="p">
                        {product['name']}
                    </Typography>
                </CardContent>
                <CardActions>
                    <Button size="small" className={classes.button} onClick={() => handleAdd(product) }>Add to carte</Button>
                </CardActions>
            </Card>

    );
};

export {Product};