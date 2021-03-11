import React from 'react';
import {CustomTable} from "../Utils/CustomTable";

const PartInventory = (props)=> {
    const {rows} = props;
    const columns = [
        { title: 'Material Name', field: 'partname' },
        { title: 'Quantity', field: 'quantity' },
        { title: 'Location', field: 'location' },
    ];

    return (
       <>
           <CustomTable
               data = {rows}
               columns = {columns}
               title ={`Part Inventory`}
           ></CustomTable>
       </>
    );
}
export {PartInventory};