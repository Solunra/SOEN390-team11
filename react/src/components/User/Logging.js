import React, {useState} from 'react';
import {CustomTable} from "../Utils/CustomTable";
import EditIcon from "@material-ui/icons/Edit";
import AddCircleRoundedIcon from "@material-ui/icons/AddCircleRounded";
import DeleteIcon from "@material-ui/icons/Delete";
import ShoppingBasketTwoToneIcon from '@material-ui/icons/ShoppingBasketTwoTone';

import BuildPath from "../RequestBuilder";
import request from 'superagent';
import CloseIcon from "@material-ui/icons/Close";

const Logging = (props) => {
    const {setLogging} = props;
    const columns = [
        { title: 'Action', field: 'vendorname' },
        { title: 'Date', field: 'type' },
        // { title: 'Customer', field: 'rawname' },
        // { title: 'Due Date', field: 'quantity'},
        // { title: 'Balance Due', field: 'status' },
    ];
    const actions = [
        {
            icon: () => { return <CloseIcon />;},
            position: "toolbar",
            export: false,
            onClick: (event, rowData) => {
                setLogging(false);
            }
        },
    ];
    return (
        <>
            <CustomTable
                columns={columns}
                actions={actions}
                title={`Logging Table`}
            >
            </CustomTable>
        </>
    );
}
export {Logging};