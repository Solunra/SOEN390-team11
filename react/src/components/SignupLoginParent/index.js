import {Link, Route, HashRouter} from 'react-router-dom';
import {Tabs, Tab} from '@material-ui/core';
import { makeStyles } from '@material-ui/core/styles';
import SignupComponent from '../Signup';
import LoginComponent from '../Login';
import HomeComponent from '../Home'
import './style.css';

const SignupLoginParentComponent = () => {
    const useStyles = makeStyles({
        root: {
          flexGrow: 1,
          maxWidth:'auto',
          background: "#0C2A5B",
          borderRadius: 3,
          boxShadow: '0 3px 5px 2px rgba(8, 24, 50, .3)',
          direction: 'column',
          alignItems: 'center',
          justify: 'center',
        },
        
        tab: {
          color: "white",
          background: "#0B254F",
          boxShadow: '0 3px 5px 2px rgba(9, 30, 64, .8)',
          borderRadius: 3,
        }
      });

    const classes = useStyles();

    return (
        <div className="content">
            <HashRouter>
                <Tabs
                    indicatorColor="secondary"
                    textColor="primary"
                    variant="standard"
                    centered
                    className = {classes.root}
                >
                    <Tab className = {classes.tab} label = "Sign Up" component={Link} to={"/account/signup"}/>
                    <Tab className = {classes.tab} label = "Log In" component={Link} to={"/account/login"}/>
                </Tabs>
                <div className="content">
                    <Route exact path = "/account" component = {HomeComponent}/>
                    <Route path = "/account/signup" component = {SignupComponent}/>
                    <Route path = "/account/login" component = {LoginComponent}/>
                </div>
            </HashRouter>
        </div>
    );
}

export default SignupLoginParentComponent;