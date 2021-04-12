import React, { useEffect, useState } from "react";
import {
    Dialog,
    DialogTitle,
    DialogContent,
    makeStyles,
    DialogContentText,
    DialogActions,
    FormControl,
    InputLabel,
    Select,
    MenuList,
    Menu,
    MenuItem,
    Box,
} from "@material-ui/core";
import Button from "@material-ui/core/Button";
import TextField from "@material-ui/core/TextField";
import request from "superagent";
import BuildPath from "../RequestBuilder";
import { Grid } from "@material-ui/core";
import { useHistory } from "react-router-dom";

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

const UserForm = (props) => {
    const history = useHistory();
    const {
        open,
        setOpen,
        userEditInfo,
        setUserEditInfo,
        loading,
        setLoading,
    } = props;
    const [error, setError] = useState("");
    const [username, setUsername] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [role, setRole] = useState("");
    const [passwordVerification, setPasswordVerification] = useState("");
    const classes = useStyles();
    const roleList = ["CUSTOMER", "ADMIN"];
    const closeError = () => {
        setTimeout(() => {
            setError("");
        }, 5000);
    };
    const handleSubmit = (e) => {
        // alert("check edit or add"+JSON.stringify(userEditInfo) !== JSON.stringify({}));
        if (JSON.stringify(userEditInfo) !== JSON.stringify({})) {
            editOperation();
        } else {
            addOperation();
        }
    };
    const addOperation = () => {
        // alert("check value add"+checkValue("add"))
        if (checkValue("add")) {
            request
                .post(BuildPath("/account/signup"))
                .set("Authorization", localStorage.getItem("Authorization"))
                .send({
                    username: username,
                    password: password,
                    email: email,
                    role: role,
                })
                .set("Accept", "application/json")
                .then((res) => {
                    if (res.status === 201) {
                        cleanUp();
                        setOpen(false);
                        setLoading(!loading);
                    }
                })
                .catch((err) => {
                    setError("Cannot Create account");
                    setTimeout(() => {
                        setError("");
                    }, 5000);
                });
        }
    };
    const editOperation = () => {
        // alert("check value edit"+checkValue("edit"))
        if (checkValue("edit")) {
            request
                .post(BuildPath("/account/edit/"))
                .set("Authorization", localStorage.getItem("Authorization"))
                .send({
                    username: !username ? userEditInfo["username"] : username,
                    password: !password ? userEditInfo["password"] : password,
                    email: !email ? userEditInfo["email"] : email,
                    role: !role ? userEditInfo["role"] : role,
                    userID: userEditInfo["userID"],
                })
                .set("Accept", "application/json")
                .then((res) => {
                    console.log(res);
                    cleanUp();
                    setOpen(false);
                    if (role === "CUSTOMER") {
                        localStorage.removeItem("Authorization");
                        history.push("/");
                    }
                })
                .catch((err) => {
                    setError("Cannot Create account");
                    setTimeout(() => {
                        setError("");
                    }, 5000);
                });
        } else {
            setError("All value is not changing, old value");
            setTimeout(() => {
                setError("");
            }, 5000);
        }
    };
    const checkValue = (type) => {
        if (type === "edit") {
            if (
                username === "" &&
                password === "" &&
                role === "" &&
                passwordVerification === ""
            ) {
                return false;
            } else {
                if (
                    username !== "" ||
                    password !== "" ||
                    role !== "" ||
                    passwordVerification !== ""
                ) {
                    return true;
                }
            }
        } else {
            if (
                !username ||
                !password ||
                !role ||
                !passwordVerification ||
                !email
            ) {
                return false;
            }
            return true;
        }

        //!username mean empty
    };
    const cleanUp = () => {
        setUsername("");
        setRole("");
        setPassword("");
        setPasswordVerification("");
        setEmail("");
        setUserEditInfo({});
    };

    const validation = (e, type) => {
        let value = e.target.value;
        if (value.trim() === "") {
            return;
        }
        switch (type) {
            case 0:
                if (
                    value.endsWith("@gmail.com") ||
                    value.endsWith("@gmail.ca")
                ) {
                    setEmail(value);
                }
                return;
            case 1:
                setUsername(value);
                return;
            case 2:
                if (value !== "None") {
                    setRole(value);
                }

                return;
            case 3:
                if (value.length > 8) {
                    let capital = /[A-Z]/;
                    let specialChar = /[*$%#]/;
                    let digit = /[0-9]/;
                    if (
                        capital.test(value) &&
                        specialChar.test(value) &&
                        digit.test(value)
                    ) {
                        setPassword(value);
                    }
                }
                return;
            case 4:
                if (password === value) {
                    setPasswordVerification(value);
                }
                return;
        }
    };

    return (
        <Dialog
            open={open}
            onClose={() => {
                setOpen(false);
            }}
            aria-labelledby="form-dialog-title"
            classes={classes.dialogWrapper}
        >
            <DialogTitle id="form-dialog-title">User From</DialogTitle>
            <DialogContent>
                <DialogContentText>Add user</DialogContentText>
                <Grid item xs={12}>
                    <div style={{ color: "red" }}>{error}</div>
                </Grid>

                <TextField
                    autoFocus
                    margin="dense"
                    defaultValue={userEditInfo["email"]}
                    onChange={(e) => {
                        validation(e, 0);
                    }}
                    label="Email"
                    fullWidth
                    variant="outlined"
                />
                <TextField
                    autoFocus
                    margin="dense"
                    defaultValue={userEditInfo["username"]}
                    onChange={(e) => {
                        validation(e, 1);
                    }}
                    label="UserName"
                    fullWidth
                    variant="outlined"
                />
                <FormControl variant="outlined" fullWidth>
                    <InputLabel>Role</InputLabel>
                    <Select
                        // value={`${vendorID===''?userEditInfo['vendorID']:vendorID}`}
                        value={userEditInfo["role"]}
                        onChange={(e) => {
                            validation(e, 2);
                        }}
                        label="Vendor"
                    >
                        <MenuItem value={`None`}>Not Assign</MenuItem>
                        {roleList.map((row) => {
                            return <MenuItem value={row}>{row}</MenuItem>;
                        })}
                    </Select>
                </FormControl>
                <TextField
                    autoFocus
                    margin="dense"
                    defaultValue={userEditInfo["password"]}
                    onChange={(e) => {
                        validation(e, 3);
                    }}
                    label="Password"
                    type="password"
                    fullWidth
                    variant="outlined"
                />
                <TextField
                    autoFocus
                    margin="dense"
                    defaultValue={userEditInfo["password"]}
                    onChange={(e) => {
                        validation(e, 4);
                    }}
                    label="Password Verification"
                    type="password"
                    fullWidth
                    variant="outlined"
                />
            </DialogContent>
            <DialogActions classes={{ root: classes.leftDialogActions }}>
                <Button
                    onClick={() => {
                        setOpen(false);
                    }}
                    color="primary"
                >
                    Cancel
                </Button>
                <Button onClick={handleSubmit} color="primary">
                    Submit
                </Button>
            </DialogActions>
        </Dialog>
    );
};
export { UserForm };
