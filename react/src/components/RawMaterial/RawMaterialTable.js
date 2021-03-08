import React, {useState} from 'react';
import request from "superagent";
import BuildPath from "../RequestBuilder";
import {CustomTable} from "../Utils/CustomTable";
import EditIcon from "@material-ui/icons/Edit";
import AddCircleRoundedIcon from "@material-ui/icons/AddCircleRounded";
import DeleteIcon from "@material-ui/icons/Delete";
import ShoppingBasketTwoToneIcon from '@material-ui/icons/ShoppingBasketTwoTone';
import BuildIcon from "@material-ui/icons/Build";
import {RawMaterialForm} from "./RawMaterialForm";

const RawMaterialTable = (props) => {
    const {rows,re_render, setRe_render } = props;
    const [open, setOpen] = useState(false);
    const [data, setData] = useState(false);
    const columns = [
        // { title: 'Id', field: 'productid' },
        { title: 'Raw material Name', field: 'name' },
        { title: 'Description', field: 'description' },
        { title: 'Vendor', field: 'venderid' },
        { title: 'Price', field: 'price'},
        { title: 'Per unit', field: 'unit' },
    ];
    const actions = [
        {
            icon: () => { return <EditIcon />;},
            export: false,
            onClick: (event, rowData) => {
                handleEdit(rowData);
            }
        },
        {
            icon: () => {return <AddCircleRoundedIcon />;},
            position: "toolbar",
            export: false,
            onClick: () => {
                handleAdd();
            }
        },
        {
            icon: () => {return <DeleteIcon />;},
            export: false,
            onClick: (event, rowData) => {
                handleDelete(rowData);
            }
        },
        {
            icon: () => {return <ShoppingBasketTwoToneIcon />;},
            export: false,
            onClick: (event, rowData) => {
                alert("order raw material");
            }
        }
    ];
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
    const handleDelete = (row) =>{
        // request
        //     .delete(BuildPath("/product/delete/"+row['productid']))
        //     .set('Authorization', localStorage.getItem("Authorization"))
        //     .set('Accept', 'application/json')
        //     .then(res => {
        //         if (res.status === 200) {
        //             setRe_render(!re_render);
        //         }
        //     })
        //     .catch(err => {
        //         setErrMessage(err.response.body['message']);
        //         setTimeout(err.response.body[''], 45000);
        //     });
    }

    return (
        <>
            <CustomTable
                data={rows}
                columns = {columns}
                handleDelete = {handleDelete}
                handleEdit = {handleEdit}
                handleAdd = {handleAdd}
                actions = {actions}
                title = {`Raw Material Table`}
            >
            </CustomTable>
            <RawMaterialForm
                open={open}
                handleClose={handleClose}
                rowData={data}
                setRowData={setData}
                re_render={re_render}
                setRe_render={setRe_render}
            >

            </RawMaterialForm>
        </>
    );
}
export {RawMaterialTable};