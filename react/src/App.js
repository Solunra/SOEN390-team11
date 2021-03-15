import SignupLoginParentComponent from './components/SignupLoginParent';
import {HashRouter, Link, Route} from "react-router-dom";
import {Tabs, Tab} from '@material-ui/core';
import IdleTimer from 'react-idle-timer'
import { useState } from 'react';
import {NavCustomer , NavAdmin } from './components/Navbar';
import {Production} from './components/Production';
import { useHistory } from "react-router-dom";
import {Inventory} from './components/Inventory';
import {RawMaterial} from "./components/RawMaterial";
import './App.css';
import {Order} from "./components/Order";

function App() {
    const [idleTimer, setIdleTimer] = useState();
    const history = useHistory();
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
              <div className="appContent">
                  <Route exact path = "/" />
                  <Route path = "/customer" component={NavCustomer}/>
                  <Route path = "/admin" component={NavAdmin}/>
                  <Route path = "/account" component = {SignupLoginParentComponent} />
                  <Route path="/customer/shop" > </Route>
                  <Route path="/customer/carte" > </Route>
                  <Route path="/customer/info" > </Route>
                  <Route path = "/admin/production" component={Production}/>
                  <Route path = "/admin/inventory" component={Inventory}/>
                  <Route path = "/admin/rawMaterial" component={RawMaterial}/>
                  <Route path = "/admin/order" component={Order}/>

              </div>
          </HashRouter>
      </div>
  );
}

export default App;