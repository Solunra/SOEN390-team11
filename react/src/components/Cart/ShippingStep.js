import React from "react";
import request from "superagent";
import BuildPath from "../RequestBuilder";
import {
    makeStyles,
    MuiThemeProvider,
    createMuiTheme,
} from "@material-ui/core/styles";
import List from "@material-ui/core/List";
import ListItem from "@material-ui/core/ListItem";
import Button from "@material-ui/core/Button";
import AddressCard from "./AddressCard";
import ShippingForm from "./ShippingForm";

const useStyles = makeStyles((theme) => ({
    root: {
        "& > *": {
            margin: theme.spacing(1),
        },
    },
}));

const theme = createMuiTheme({
    overrides: {
        MuiListItem: {
            root: {
                "&": { width: "initial" },
            },
        },
    },
});

const flexContainer = {
    display: "flex",
    flexDirection: "row",
    gap: "20px",
};

const ShippingStep = ({ setCustomerId, setCustomerInfo }) => {
    const classes = useStyles();
    const [open, setOpen] = React.useState(false);
    const [selected, setSelected] = React.useState(null);
    const [addresses, setAddresses] = React.useState([]);

    const updateAddressList = () => {
        request
            .get(BuildPath("/account/customers"))
            .set("Authorization", localStorage.getItem("Authorization"))
            .accept("application/json")
            .then((res) => {
                if (res.status === 200) {
                    setAddresses(res.body);
                }
            })
            .catch((err) => {
                console.error(err);
            });
    };

    React.useEffect(() => {
        if (open === false) updateAddressList();
    }, [open]);

    const Select = (customerID, address) => {
        setSelected(customerID);
        setCustomerId(customerID);
        setCustomerInfo(address);
    };

    return (
        <MuiThemeProvider theme={theme}>
            <div className={classes.root}>
                <List style={flexContainer}>
                    {addresses.map((address, i) => (
                        <ListItem key={i}>
                            <AddressCard
                                address={address}
                                selected={selected}
                                Select={Select}
                                updateAddressList={updateAddressList}
                            />
                        </ListItem>
                    ))}
                    <ListItem />
                </List>
                <Button variant="outlined" onClick={() => setOpen(true)}>
                    Add New Address
                </Button>
            </div>
            <ShippingForm
                open={open}
                setOpen={setOpen}
                setShipping={setCustomerId}
                updateAddressList={updateAddressList}
            />
        </MuiThemeProvider>
    );
};
export default ShippingStep;
