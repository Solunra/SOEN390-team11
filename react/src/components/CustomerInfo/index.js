import {
    Card,
    CardActions,
    CardContent,
    Grid,
    makeStyles,
} from "@material-ui/core";
import React, { useEffect, useState } from "react";
import request from "superagent";
import BuildPath from "../RequestBuilder";
import Button from "@material-ui/core/Button";
import Typography from "@material-ui/core/Typography";
import { EditForm } from "./EditForm";

const useStyles = makeStyles((theme) => ({
    rootGrid: {
        flexGrow: 1,
        width: "100%",
        height: "100%",
        marginTop: "50px",
    },
    button: {
        backgroundColor: "#66ccff",
    },
}));
const UserInfo = () => {
    const [userInfo, setUserInfo] = useState({});
    const [open, setOpen] = useState(false);
    const [loading, setLoading] = useState(true);
    const classes = useStyles();
    const getUserInfo = () => {
        request
            .get(BuildPath("/account/loggedUser"))
            .set("Authorization", localStorage.getItem("Authorization"))
            .set("Accept", "application/json")
            .then((res) => {
                console.log(res.body);
                if (res.status === 200) {
                    setUserInfo(res.body);
                }
            })
            .catch((err) => {
                console.log(err);
            });
    };

    useEffect(() => {
        getUserInfo();
    }, [loading]);
    return (
        <>
            <Card className={classes.rootGrid}>
                <CardContent>
                    <Typography
                        className={classes.title}
                        color="textSecondary"
                        gutterBottom
                    >
                        Account information
                    </Typography>
                    <Typography
                        variant="h5"
                        component="h2"
                        className={classes.rootGrid}
                    >
                        Username: {userInfo["username"]}
                    </Typography>
                    <Typography
                        variant="h5"
                        component="h2"
                        className={classes.rootGrid}
                    >
                        Email: {userInfo["email"]}
                    </Typography>
                </CardContent>
                <CardActions className={classes.rootGrid}>
                    <Button
                        size="small"
                        onClick={() => {
                            setOpen(true);
                        }}
                        className={classes.button}
                    >
                        Edit
                    </Button>
                </CardActions>
            </Card>
            <EditForm
                open={open}
                setOpen={setOpen}
                userEditInfo={userInfo}
                setUserEditInfo={setUserInfo}
                loading={loading}
                setLoading={setLoading}
            />
        </>
    );
};
export { UserInfo };
