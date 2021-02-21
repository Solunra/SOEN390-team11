import './style.css';
import DrawerLeft from "./LeftDrawer";

const NavCustomer = () => {

    return (
        <div>
            <DrawerLeft role="customer"/>
        </div>
    );
}

const NavAdmin = () => {
    return (
        <div>
            <DrawerLeft role="admin"/>
        </div>
    );
}
export {NavCustomer , NavAdmin};
