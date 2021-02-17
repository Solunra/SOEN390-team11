import SignupLoginParentComponent from './components/SignupLoginParent';
import {HashRouter, Link, Route} from "react-router-dom";
import {Tabs, Tab} from '@material-ui/core';
import IdleTimer from 'react-idle-timer'
import { useState } from 'react';
import {NavCustomer , NavAdmin } from './components/Navbar';

function App() {
    const [idleTimer, setIdleTimer] = useState();

  return (
      <div>
          <IdleTimer 
                ref={ref => {setIdleTimer(ref)}}
                // In milliseconds
                timeout={1000 * 60 * 30}
                onIdle={handleOnIdle}
                onAction={handleOnAction}
                onActive={handleOnActive}
                debounce={250}
          />
          <HashRouter>
              <Tabs
                  indicatorColor="secondary"
                  textColor="primary"
                  variant="standard"
                  centered
              >
                  <Tab  label = "Home" component={Link} to={"/"}/>
                  <Tab  label = "Shop" component={Link} to={"/shop"}/>
                  <Tab  label = "Account" component={Link} to={"/account"}/>
              </Tabs>
              <div className="content">
                  <Route exact path = "/" />
                  <Route path = "/home" component={NavCustomer}/>
                  <Route path = "/admin" component={NavAdmin}/>
                  <Route path = "/shop" />
                  <Route path = "/account" component = {SignupLoginParentComponent} />
              </div>
          </HashRouter>
      </div>
  );
}

function handleOnIdle(event) {
    localStorage.removeItem("Authorization");
    history.push("/");
}
function handleOnAction(event) {
    // Do nothing
}
function handleOnActive(event) {
    // Do nothing
}

export default App;
