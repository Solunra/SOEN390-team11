import React, { useState } from 'react'
import request from 'superagent'
import BuildPath from '../RequestBuilder'
import {useHistory} from 'react-router-dom';
import './style.css'
import {AlertErr} from "../Utils/AlertErr";
import {Grid} from "@material-ui/core";

const SignupComponent = () => {
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [passwordVerification, setPasswordVerification] = useState('');
    const history = useHistory();
    const [errMessage, setErrMessage] = useState('');
    const closeAlert = ()=>{
        setErrMessage('');
    }

    const handleSubmit = (event) => {
        event.preventDefault();

        request
            .post(BuildPath("/account/signup"))
            .send(
                {
                    "username": username,
                    "password": password,
                    "email": email
                }
            )
            .set('Accept', 'application/json')
            .then(res => {
                console.log(res);
                if (res.status === 201)
                {
                    history.push("/account/login");
                }
            })
            .catch(err =>{
                setErrMessage("Cannot Create account");
                setTimeout(()=>{
                    setErrMessage("")
                }, 5000);
            });


        event.target.reset();
    }

    const isDisabled = () => {
        if (!username || !email || !password || !passwordVerification)
        {
            return true;
        }
        else
        {
            if (password === passwordVerification)
            {
                return false;
            }
            return true;
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
                            Email:
                        </label>
                    </th>
                    <th>
                        <input 
                            type = "text"
                            onChange = {e => setEmail(e.target.value)} 
                        />
                    </th>
                </tr>
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
                        <label>
                            Password Verification:
                        </label>
                    </th>
                    <th>
                        <input 
                        type = "password"
                        onChange = {e => setPasswordVerification(e.target.value)} 
                        />
                    </th>
                </tr>
                <tr>
                    <th>
                        <Grid>
                            <Grid item xs={12}>
                                <div style={ {color: 'red' }}>{errMessage}</div>
                            </Grid>
                        </Grid>
                    </th>
                </tr>
                </tbody>
            </table>
            <button type="submit" disabled={isDisabled()}>Submit</button>

        </form>

    </div>
    );
}

export default SignupComponent;