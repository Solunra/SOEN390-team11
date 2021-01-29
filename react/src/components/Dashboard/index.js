
import {NavCustomer , NavAdmin } from '../Navbar';
import {BrowserRouter as Router} from 'react-router-dom';

const AppCustomer = () => {
    return (
        <Router>
            <NavCustomer />
        </Router> 
    );
}

const AppAdmin = () => {
    return (
        <Router>
            <NavAdmin />

        </Router> 
    );
}
export { AppCustomer, AppAdmin };
