import SignupLoginParentComponent from './components/SignupLoginParent';
import {HashRouter, Link, Route} from "react-router-dom";
import {Tabs, Tab} from '@material-ui/core';


function App() {
  return (
      <div>
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
                  <Route path = "/shop" />
                  <Route path = "/account" component = {SignupLoginParentComponent} />
              </div>
          </HashRouter>
      </div>
  );
}

export default App;
