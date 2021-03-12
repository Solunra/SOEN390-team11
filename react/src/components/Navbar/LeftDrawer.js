import React from "react";
import {
    Drawer,
    ListItem,
    List,
    ListItemIcon,
    ListItemText, AppBar, Toolbar,
} from "@material-ui/core";
import { makeStyles } from "@material-ui/core/styles";
import {useHistory} from "react-router-dom";
import ExitToAppRoundedIcon from '@material-ui/icons/ExitToAppRounded';
import AddShoppingCartRoundedIcon from '@material-ui/icons/AddShoppingCartRounded';
import StorefrontRoundedIcon from '@material-ui/icons/StorefrontRounded';
import ListAltOutlinedIcon from '@material-ui/icons/ListAltOutlined';
import BuildOutlinedIcon from '@material-ui/icons/BuildOutlined';
import GrainIcon from '@material-ui/icons/Grain';

const useStyles = makeStyles({
    drawer: {
        width: "190px"
    }
});

const LeftDrawer = props => {
    const history = useHistory();
    const logout = () => {
        localStorage.removeItem("Authorization");
        history.push("/");
    }
    const {role} = props;
    const classes = useStyles();
    const handleClick = (dir)=>{
        history.entries = [];
        history.index = -1;
        history.push("/"+role+"/"+dir);
    }
    let itemsList ;
    if(role === "customer"){
        itemsList = [
            {
                text: "shop",
                icon: <StorefrontRoundedIcon />,
                onClick: () => {
                    handleClick("shop");
                }
            },
            {
                text: "Logout",
                icon: <ExitToAppRoundedIcon />,
                onClick: () => logout()
            }
        ];
    }
    else if (role === "admin"){
        itemsList = [
            {
                text: "production",
                icon: <BuildOutlinedIcon />,
                onClick: () => {
                    handleClick("production")
                }
            },
            {
                text: "inventory",
                icon: <ListAltOutlinedIcon />,
                onClick: () =>{
                    handleClick("inventory")
                }
            },
            {
                text: "Raw Material",
                icon: <GrainIcon />,
                onClick: () =>{
                    handleClick("rawMaterial");
                }
            },
            {
                text: "Logout",
                icon: <ExitToAppRoundedIcon />,
                onClick: () => logout()
            }
        ];
    }

    return (
        <div>
            <AppBar position="fixed">
                <Toolbar style={{color: "black"}}>
                </Toolbar>
            </AppBar>
            <Drawer variant="permanent" className={classes.drawer}>
                <List>
                    {itemsList.map((item, index) => {
                        const { text, icon, onClick } = item;
                        return (
                            <ListItem button key={text} onClick={onClick}>
                                {icon && <ListItemIcon>{icon}</ListItemIcon>}
                                <ListItemText primary={text} />
                            </ListItem>
                        );
                    })}
                </List>
            </Drawer>
        </div>
    );
};

export default LeftDrawer;