import SignupLoginParentComponent from "./components/SignupLoginParent";
import { HashRouter, Link, Route } from "react-router-dom";
import {
    Tabs,
    Tab,
    Dialog,
    DialogTitle,
    DialogActions,
    DialogContentText,
    DialogContent,
    makeStyles,
} from "@material-ui/core";
import IdleTimer from "react-idle-timer";
import React, { useRef, useState } from "react";
import { NavCustomer, NavAdmin } from "./components/Navbar";
import { Production } from "./components/Production";
import { useHistory } from "react-router-dom";
import { Inventory } from "./components/Inventory";
import { RawMaterial } from "./components/RawMaterial";
import "./App.css";
import { Order } from "./components/Order";
import { Machinery } from "./components/Machinery";
import { Shop } from "./components/Shop";
import { PublicShop } from "./components/Shop/PublicShop";
import { Account } from "./components/Account";
import { Users } from "./components/User";
import Button from "@material-ui/core/Button";
import Logs from "./components/Logs";
import { CustomerOrder } from "./components/CustomerOrder";
import { UserInfo } from "./components/CustomerInfo";

const useStyles = makeStyles((theme) => ({
    dialogWrapper: {
        width: "75%",
    },
    dialogAction: {
        justifyContent: "flex-start",
    },
    leftDialogActions: {
        justifyContent: "flex-start",
    },
}));

const DialogIdle = () => {
    const [loggedIn, setLoggedIn] = useState(true);
    const [open, setOpen] = useState(false);
    const [idleTimer, setIdleTimer] = useState();
    const sessionTimeoutRef = useRef(null);
    const history = useHistory();
    const classes = useStyles();
    const handleOnIdle = (event) => {
        setOpen(true);
        sessionTimeoutRef.current = setTimeout(logout, 5000);
    };
    function handleOnAction(event) {
        // Do nothing
    }
    function handleOnActive(event) {
        // Do nothing
    }
    const logout = () => {
        localStorage.removeItem("Authorization");
        setOpen(false);
        history.push("/");
        setLoggedIn(false);
    };
    const keepLogin = () => {
        setOpen(false);
        clearTimeout(sessionTimeoutRef.current);
    };
    return (
        <div>
            {loggedIn && (
                <div>
                    <IdleTimer
                        ref={(ref) => {
                            setIdleTimer(ref);
                        }}
                        // In milliseconds
                        timeout={1000 * 60 * 30}
                        // timeout={1000 * 5}// if you want to test with the TA
                        onIdle={handleOnIdle}
                        onAction={handleOnAction}
                        onActive={handleOnActive}
                        debounce={250}
                    />
                    <Dialog
                        open={open}
                        aria-labelledby="form-dialog-title"
                        classes={classes.dialogWrapper}
                        fullWidth
                    >
                        <DialogTitle id="form-dialog-title">
                            You have been idled for 30 minute
                        </DialogTitle>
                        <DialogContent>
                            <DialogContentText>
                                {" "}
                                Do you want to stay or log out, if you do not
                                choose in 10 seconds, it would automatically log
                                out
                            </DialogContentText>
                        </DialogContent>
                        <DialogActions
                            classes={{ root: classes.leftDialogActions }}
                        >
                            <Button onClick={keepLogin} color="primary">
                                Keep LogIn
                            </Button>
                            <Button onClick={logout} color="primary">
                                Logout
                            </Button>
                        </DialogActions>
                    </Dialog>
                </div>
            )}
        </div>
    );
};

const App = () => {
    return (
        <div>
            <HashRouter>
                <Tabs
                    indicatorColor="secondary"
                    textColor="primary"
                    variant="standard"
                    centered
                >
                    <Tab label="Home" component={Link} to={"/"} />
                    <Tab label="Shop" component={Link} to={"/shop"} />
                    <Tab label="Account" component={Link} to={"/account"} />
                </Tabs>
                <div className="appContent">
                    <DialogIdle />
                    <Route exact path="/" />
                    <Route path="/shop" component={PublicShop} />
                    <Route path="/customer" component={NavCustomer} />
                    <Route path="/admin" component={NavAdmin} />
                    <Route
                        path="/account"
                        component={SignupLoginParentComponent}
                    />
                    <Route path="/customer/shop" component={Shop} />
                    <Route
                        path="/customer/customerorder"
                        component={CustomerOrder}
                    />
                    <Route path="/customer/info" component={UserInfo} />
                    <Route path="/admin/production" component={Production} />
                    <Route path="/admin/inventory" component={Inventory} />
                    <Route path="/admin/rawMaterial" component={RawMaterial} />
                    <Route path="/admin/order" component={Order} />
                    <Route path="/admin/Machinery" component={Machinery} />
                    <Route path="/admin/accounts" component={Account} />
                    <Route path="/admin/users" component={Users} />
                    <Route path="/admin/logs" component={Logs} />
                </div>
            </HashRouter>
        </div>
    );
};

export default App;
