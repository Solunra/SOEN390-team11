import './style.css';
import {Link, Route, Switch} from 'react-router-dom';
import { useHistory } from "react-router-dom";

const NavCustomer = () => {
    const history = useHistory();
    const logout = () => {
        localStorage.removeItem("Authorization");
        history.push("/");
    }

    return (
        <div>
            <nav className="navbar">
                <h3>
                    Logo
                </h3>
                <ul className="nav-link" >
                    <Link to="/account/shop">
                        <li>Shop</li>
                    </Link>
                    <Link to="/account/carte">
                        <li>Carte</li>
                    </Link>
                    <Link to="/account/info">
                        <li>Info</li>
                    </Link>
                    <Link>
                        <button onClick={logout}>Logout</button>
                    </Link>
                </ul>
            </nav>

            <Switch>
                <Route path="/account/shop" > </Route>
                <Route path="/account/carte" > </Route>
                <Route path="/account/info" > </Route>
            </Switch>
        </div>
    );
}

const NavAdmin = () => {
    const history = useHistory();
    const logout = () => {
        localStorage.removeItem("Authorization");
        history.push("/");
    }

    return (
        <div>
            <nav className="navbar">
                <ul className="nav-link" >
                    <Link to="/account/planning">
                        <li>Planning</li>
                    </Link>
                    <Link to="/account/scheduling">
                        <li>Scheduling</li>
                    </Link>
                    <Link to="/account/vendor">
                        <li>Vendor</li>
                    </Link>
                    <Link to="/account/production">
                        <li>Production</li>
                    </Link>
                    <Link to="/account/Quality">
                        <li>Quality</li>
                    </Link>
                    <Link to="/account/packaging">
                        <li>Packaging</li>
                    </Link>
                    <Link to="/account/shipping">
                        <li>Shipping</li>
                    </Link>
                    <Link to="/account/sale">
                        <li>Sale</li>
                    </Link>
                    <Link to="/account/accounting">
                        <li>accounting</li>
                    </Link>
                    <Link to="/account/useraccount">
                        <li>User Account</li>
                    </Link>
                    <Link >
                        <button onClick={logout}>Logout</button>
                    </Link>
                </ul>
            </nav>
            <Switch>
                <Route path="/account/purchase" > </Route>
                <Route path="/account/inventory" > </Route>
                <Route path="/account/info" > </Route>
            </Switch>
        </div>
    );
}
export {NavCustomer , NavAdmin};


