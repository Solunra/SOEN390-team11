import { useState } from 'react'
import request from 'superagent'
import BuildPath from '../RequestBuilder'
import {useHistory} from 'react-router-dom';
import './style.css'

const SignupComponent = () => {
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [passwordVerification, setPasswordVerification] = useState('');
    const history = useHistory();

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
                </tbody>
            </table>
            <button type="submit" disabled={isDisabled()}>Submit</button>
        </form>
    </div>
    );
}

export default SignupComponent;