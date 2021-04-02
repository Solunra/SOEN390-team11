import React, { useState } from 'react'
import request from 'superagent'
import BuildPath from '../RequestBuilder'
import './style.css'
import { useHistory } from 'react-router-dom'
import { Grid } from '@material-ui/core'
import { AlertErr } from '../Utils/AlertErr'

const LoginComponent = () => {
  const [username, setUsername] = useState('')
  const [password, setPassword] = useState('')
  const history = useHistory()
  const [errMessage, setErrMessage] = useState('')
  const closeAlert = () => {
    setErrMessage('')
  }
  const handleSubmit = event => {
    event.preventDefault()
    request
      .post(BuildPath('/account/signin'))
      .send({
        username: username,
        password: password
      })
      .set('Accept', 'application/json')
      .then(res => {
        if (res.status === 200) {
          localStorage.setItem('Authorization', res.headers['authorization'])
          if (res.headers['role'] === 'CUSTOMER') {
            history.push('/customer')
          } else {
            history.push('/admin')
          }
        }
      })
      .catch(err => {
        console.log(err)
      })
    event.target.reset()
  }

  const isDisabled = () => {
    return !username || !password
  }

  return (
    <div>
      <form onSubmit={handleSubmit}>
        <table>
          <tbody>
            <tr>
              <th>
                <label>Username:</label>
              </th>
              <th>
                <input
                  type='text'
                  onChange={e => setUsername(e.target.value)}
                />
              </th>
            </tr>
            <tr>
              <th>
                <label>Password:</label>
              </th>
              <th>
                <input
                  type='password'
                  onChange={e => setPassword(e.target.value)}
                />
              </th>
            </tr>
            <tr>
              <th>
                <button type='submit' disabled={isDisabled()}>
                  Submit
                </button>
              </th>
            </tr>
          </tbody>
        </table>
      </form>
      <Grid>
        <Grid item xs={12}>
          {errMessage !== '' && (
            <AlertErr message={errMessage} closeAlert={closeAlert} />
          )}
        </Grid>
      </Grid>
    </div>
  )
}

export default LoginComponent
