import React, { useState } from "react";
import request from "superagent";
import BuildPath from "../RequestBuilder";
import { useHistory } from "react-router-dom";
import "./style.css";
import { AlertErr } from "../Utils/AlertErr";
import { Grid } from "@material-ui/core";

const SignupComponent = () => {
    const [username, setUsername] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [passwordVerification, setPasswordVerification] = useState("");
    const history = useHistory();
    const [errMessage, setErrMessage] = useState("");
    const closeErrMessage = () => {
        setTimeout(() => {
            setErrMessage("");
        }, 4000);
    };

    const handleSubmit = (event) => {
        if (!checkValue()) {
            event.preventDefault();

            request
                .post(BuildPath("/account/signup"))
                .send({
                    username: username,
                    password: password,
                    email: email,
                })
                .set("Accept", "application/json")
                .then((res) => {
                    console.log(res);
                    if (res.status === 201) {
                        history.push("/account/login");
                    }
                })
                .catch((err) => {
                    setErrMessage("Cannot Create account");
                    setTimeout(() => {
                        setErrMessage("");
                    }, 5000);
                });

            event.target.reset();
        }
    };

    const checkValue = () => {
        if (!username || !email || !password || !passwordVerification) {
            let error = "";
            if (!username && !email && !password && !passwordVerification) {
                error = "All the field is empty";
            } else if (!username) {
                error = "Username is empty";
            } else if (!email) {
                error = "Email is invalid";
            } else if (!password) {
                error =
                    "Password is invalid/Empty. \nPassword at least 8 character, \nhave one Capital char and one special char";
            }
            setErrMessage(error);
            closeErrMessage();
            return true;
        } else {
            if (password === passwordVerification) {
                return false;
            }
            return true;
        }
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
            case 3:
                if (password === value) {
                    setPasswordVerification(value);
                }
                return;
        }
    };

    return (
        <div>
            <form onSubmit={handleSubmit}>
                <table>
                    <tbody>
                        <tr>
                            <th>
                                <label>Email:</label>
                            </th>
                            <th>
                                <input
                                    type="text"
                                    onChange={(e) => {
                                        validation(e, 0);
                                    }}
                                />
                            </th>
                        </tr>
                        <tr>
                            <th>
                                <label>Username:</label>
                            </th>
                            <th>
                                <input
                                    type="text"
                                    onChange={(e) => {
                                        validation(e, 1);
                                    }}
                                />
                            </th>
                        </tr>
                        <tr>
                            <th>
                                <label>Password:</label>
                            </th>
                            <th>
                                <input
                                    type="password"
                                    onChange={(e) => {
                                        validation(e, 2);
                                    }}
                                />
                            </th>
                        </tr>
                        <tr>
                            <th>
                                <label>Password Verification:</label>
                            </th>
                            <th>
                                <input
                                    type="password"
                                    onChange={(e) => {
                                        validation(e, 3);
                                    }}
                                />
                            </th>
                        </tr>
                        <tr>
                            <th>
                                <Grid>
                                    <Grid item xs={12}>
                                        <div style={{ color: "red" }}>
                                            {errMessage}
                                        </div>
                                    </Grid>
                                </Grid>
                            </th>
                        </tr>
                        <tr>
                            <th>
                                <button type="submit">Submit</button>
                            </th>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    );
};

export default SignupComponent;
