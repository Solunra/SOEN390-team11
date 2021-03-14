import React, {useState} from 'react';
import {CustomTable} from "../Utils/CustomTable";
import EditIcon from "@material-ui/icons/Edit";
import AddCircleRoundedIcon from "@material-ui/icons/AddCircleRounded";
import DeleteIcon from "@material-ui/icons/Delete";
import ShoppingBasketTwoToneIcon from '@material-ui/icons/ShoppingBasketTwoTone';

import BuildPath from "../RequestBuilder";
import request from 'superagent';

const OrderTable = (props) => {
    const {rows,re_render, setRe_render } = props;
    const [open, setOpen] = useState(false);
    const [data, setData] = useState({});
    const [errMessage, setErrMessage] = useState('');
    console.log(rows);
    const columns = [
        { title: 'Vendor', field: 'vendorname' },
        { title: 'Type', field: 'type' },
        { title: 'Type Name', field: 'rawname' },
        { title: 'Quantity', field: 'quantity'},
        { title: 'Status', field: 'status' },
    ];
    // const actions = [
    //     {
    //         icon: () => { return <EditIcon />;},
    //         export: false,
    //         onClick: (event, rowData) => {
    //             handleEdit(rowData);
    //         }
    //     },
    // ];
    const handleClose = () => {
        setOpen(false);
    };
    const handleEdit = (row) =>{
        setData(row);
        setOpen(true);
    }
    const handleAdd = () =>{
        setData({});
        setOpen(true);
    }

    return (
        <>
            <CustomTable
                data={rows}
                columns = {columns}
                title = {`Order Table`}
            >
            </CustomTable>
        </>
    );
}
export {OrderTable};