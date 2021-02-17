import React, { useState } from 'react';
import request from 'superagent';
import BuildPath from '../RequestBuilder'
import './style.css'
import { useHistory } from "react-router-dom";

const LoginComponent = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const history = useHistory();

    const handleSubmit = (event) => {
        event.preventDefault();
        request
            .post(BuildPath("/account/signin"))
            .send(
                {
                    "username": username,
                    "password": password,
                }
            )
            .set('Accept', 'application/json')
            .then(res => {
                console.log(res.headers["authorization"]);
                console.log(res.headers["role"]);
                if (res.status === 200)
                {
                    localStorage.setItem("Authorization", res.headers["authorization"]);
                    console.log(localStorage.getItem("Authorization"))
                    if(res.headers["role"] === "CUSTOMER"){
                        history.push("/home");
                    }
                    else{
                        history.push("/admin");
                    }

                }
            })
            .catch(err => {
                console.log(err);
            })
        ;
        event.target.reset();
        console.log("Login form successful");
        console.log(BuildPath("/account/signin"));
    }

    const isDisabled = () => {
        if (!username || !password) {
            return true;
        }
        else {
            return false;
        }
    }

    return ( 
    <div>
        <form onSubmit={handleSubmit}>
            <table>
                <tbody>
                <tr>
                    <th>
                        <label>
                            Username:
                        </label>
                    </th>
                    <th>
                        <input 
                            type = "text"
                            onChange = {e => setUsername(e.target.value)} 
                        />
                    </th>
                </tr>
                <tr>
                    <th>
                        <label>
                            Password:
                        </label>
                    </th>
                    <th>
                        <input 
                            type = "password"
                            onChange = {e => setPassword(e.target.value)} 
                        />
                    </th>
                </tr>
                <tr>
                    <th>
                        <button type="submit" disabled={isDisabled()}>Submit</button>
                    </th>
                </tr>
                </tbody>
            </table>
        </form>
    </div>
    );
}

export default LoginComponent;