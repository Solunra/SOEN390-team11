import { Grid, makeStyles } from "@material-ui/core";
import React, { useEffect, useState } from "react";
import request from "superagent";
import BuildPath from "../RequestBuilder";
import Button from "@material-ui/core/Button";
import { CustomTable } from "../Utils/CustomTable";
import { AssignmentInd } from "@material-ui/icons";
import AddCircleRoundedIcon from "@material-ui/icons/AddCircleRounded";
import { UserForm } from "./UserForm";
import { AssignRole } from "./AssignRole";

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
const Users = () => {
    const [logging, setLogging] = useState(false);
    const [addUser, setAddUser] = useState(false);
    const [assignRole, setAssignRole] = useState(false);
    const [useEditInfo, setUseEditInfo] = useState({});
    const [userList, setUserList] = useState([]);
    const [loading, setLoading] = useState(true);
    const [logList, setLogList] = useState([]);
    const classes = useStyles();
    const columns = [
        { title: "User name", field: "username" },
        { title: "Email", field: "email" },
        { title: "Role", field: "role" },
    ];
    const getUserList = () => {
        request
            .get(BuildPath("/account/allUser"))
            .set("Authorization", localStorage.getItem("Authorization"))
            .set("Accept", "application/json")
            .then((res) => {
                if (res.status === 200) {
                    if (JSON.stringify(userList) !== JSON.stringify(res.body)) {
                        setUserList(res.body);
                    }
                }
            })
            .catch((err) => {
                console.log(err);
            });
    };
    const getLogList = (username) => {
        request
            .get(BuildPath("/account/logging/" + username))
            .set("Authorization", localStorage.getItem("Authorization"))
            .set("Accept", "application/json")
            .then((res) => {
                if (res.status === 200) {
                    if (JSON.stringify(logList) !== JSON.stringify(res.body)) {
                        setLogList(res.body);
                    }
                }
            })
            .catch((err) => {
                console.log(err);
            });
    };
    useEffect(() => {
        getUserList();
    }, [loading]);
    // change the role , allow them to change the information of them self
    const actions = [
        {
            icon: () => {
                return <AssignmentInd />;
            },
            export: false,
            onClick: (event, rowData) => {
                // alert(JSON.stringify(rowData));
                setUseEditInfo(rowData);
                setAssignRole(true);
            },
        },
        {
            icon: () => {
                return <AddCircleRoundedIcon />;
            },
            position: "toolbar",
            export: false,
            onClick: (event, rowData) => {
                setAddUser(true);
                setUseEditInfo({});
            },
        },
    ];
    const editInfo = () => {
        request
            .get(BuildPath("/account/loggedUser"))
            .set("Authorization", localStorage.getItem("Authorization"))
            .set("Accept", "application/json")
            .then((res) => {
                setUseEditInfo(res.body);
                setAddUser(true);
            })
            .catch((err) => {
                console.log(err);
            });
    };
    return (
        <div className={classes.rootGrid}>
            <Grid container spacing={3}>
                <Grid item md={12}>
                    <Button onClick={editInfo} className={classes.button}>
                        Edit Info
                    </Button>
                </Grid>
                <Grid item xs={12}>
                    <CustomTable
                        columns={columns}
                        actions={actions}
                        data={userList}
                        title={`Users Table`}
                    />
                    <UserForm
                        open={addUser}
                        setOpen={setAddUser}
                        userEditInfo={useEditInfo}
                        setUserEditInfo={setUseEditInfo}
                        loading={loading}
                        setLoading={setLoading}
                    />
                    <AssignRole
                        open={assignRole}
                        setOpen={setAssignRole}
                        userInfo={useEditInfo}
                        setUserInfo={setUseEditInfo}
                        loading={loading}
                        setLoading={setLoading}
                    />
                </Grid>
            </Grid>
        </div>
    );
};
export { Users };
