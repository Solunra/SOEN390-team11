import {PlayCircleFilled, PauseCircleFilled} from '@material-ui/icons';
import React, {useState} from 'react';
import {CustomTable} from "../Utils/CustomTable";

// Return a machine table that will display the characteristics of a machine (ID, name, product)
const MachineryTable = (props) => {
    const {rows} = props;
    const [data, setData] = useState({});
    
    
   /* const rowData = [
        {"machineid":1,"name":"","productName": "product name"},
        {"machineid":2,"name":"machine name","productName": "product name"},
        {"machineid":3,"name":"machine name","productName": "product name"},
        {"machineid":4,"name":"machine name","productName": "product name"},
        {"machineid":5,"name":"machine name","productName": "product name"},
    ];*/

    const columns = [
        {title: 'Machine id', field: "machineid"},
        {title: 'Name', field: "name"},
        {title: 'Product Name', field: "productName"}
    ];

    const actions = [
        {
            icon: () => {return <PlayCircleFilled />},
            export: false,
            onClick: (event, rowData) => {
                alert("Start");
            }
        },

        {
            icon: () => {return <PauseCircleFilled />},
            export: false,
            onClick: (event, rowData) => {
                alert("Pause"); 
        }

        }
    ];

    const handleEdit = (row) =>{ 
        setData(row);
        console.log(row);
    }

    const handleDelete = (row) => {
        alert(row)
    }

    const handleAdd = () =>{
        setData({});
    }

    return (
        <>

            <CustomTable
                data={rows}
                columns = {columns}
                handleEdit = {handleEdit}
                handleAdd ={handleAdd}
                handleDelete = {handleDelete}
                actions = {actions}
                title = {"Machinery Table"}
            >
            </CustomTable>

    </>
    
    );

}

export {MachineryTable}; 