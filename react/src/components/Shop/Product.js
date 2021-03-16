import React from 'react';
import bike from "../Utils/bike.png";
import {Avatar, Card, CardActions, CardContent, CardHeader, CardMedia, makeStyles, Paper} from "@material-ui/core";
import Typography from "@material-ui/core/Typography";
import Button from "@material-ui/core/Button";
import Grid from "@material-ui/core/Grid";

const useStyles = makeStyles(theme => ({

    cardMedia: {
        height: 0,
        paddingTop: '56.25%',
    },
    button:{
        backgroundColor: '#66ccff'
    }
}))

const Product = props => {
    const {product, handleAdd,role} = props;
    const classes = useStyles();
    return (

            <Card className={classes.container}>
                <CardHeader
                    title={product['name']}
                    subheader={`\$ ${product['price']}`}
                />
                <CardMedia className={classes.cardMedia} image={bike} />
                <CardContent>
                    <Typography variant="body2" component="p">
                        {product['color']}
                    </Typography>
                    <Typography variant="body2" component="p">
                        {product['finish']}
                    </Typography>
                    <Typography variant="body2" component="p">
                        {product['grade']}
                    </Typography>
                    <Typography variant="body2" component="p">
                        {product['size']}
                    </Typography>
                </CardContent>
                <CardActions>
                    {role !== "public" && <Button size="small" className={classes.button} onClick={() => handleAdd(product) }>Add to carte</Button>}
                </CardActions>
            </Card>

    );
};

export {Product};