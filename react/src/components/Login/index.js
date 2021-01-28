import { useState } from 'react';
import request from 'superagent';
import BuildPath from '../RequestBuilder'
import './style.css'

const LoginComponent = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleSubmit = (event) => {
        event.preventDefault();

        // request
        //     .post(BuildPath("/account/signin"))
        //     .send(
        //         {
                    
        //         }
        //     )
        //     .set('Accept', 'application/json')
        //     .then(res => {
        //         console.log(res.body);
        //     });

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