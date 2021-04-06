import React, {useEffect, useState} from 'react';
import {CustomTable} from "../Utils/CustomTable";
import request from "superagent";
import BuildPath from "../RequestBuilder";
import {Grid} from "@material-ui/core";
import AccountCircleIcon from '@material-ui/icons/AccountCircle';

// Return a machine table that will display the characteristics of a machine (ID, name, product)
const CustomerOrderTable = (props) => {
    const [data, setData] = useState({});
    const [customerOrderList, setCustomerOrderList] = useState([]);
    const [re_render, setRe_render] = useState(true);
    const [error,setError]=useState('');
    const getCustomerOrder = () =>{
        request
            .get(BuildPath("/customerOrder/"))
            .set('Authorization', localStorage.getItem("Authorization"))
            .set('Accept', 'application/json')
            .then(res => {
                if (res.status === 200)
                {
                    if(JSON.stringify(customerOrderList) !== JSON.stringify(res.body)){
                        setCustomerOrderList(res.body);
                    }
                }
            })
            .catch(err => {
                console.log(err);
            });
    };
    useEffect(() => {
        getCustomerOrder();
    },[re_render]);


    const columns = [
        {title: 'Order description', field: "orderdescription"},
        {title: 'Quantity', field: "quantity"},
        {title: 'Order date', field: "orderdate"},
        {title: 'Delivery date', field: "deliverydate"},
        {title: 'Cost', field: "cost"}
    ];
  
    return (
        <>
            <CustomTable
                data={customerOrderList}
                columns={columns}
                title = {`Customer order `}
            >
            </CustomTable>
            <Grid item xs={12}>
                <div style={ {color: 'red' }}>{error}</div>
            </Grid>
    </>
    
    );

}



export {CustomerOrderTable}; 