import React from 'react';
import CloseIcon from "@material-ui/icons/Close";
import {CustomTable} from "../Utils/CustomTable";

const MaterialTable = (props)=> {
    const {rows, setMaterialTable ,partMaterialTitle} = props;
    const columns = [
        { title: 'Material Name', field: 'materialname' },
        { title: 'Quantity', field: 'materialQuantity' },
    ];
    const actions = [
        {
            icon: () => {return <CloseIcon />;},
            position: "toolbar",
            export: false,
            onClick: () => {
                setMaterialTable(false);
            }
        },
    ];
    return (
        <>
            <CustomTable
                data={rows}
                columns ={columns}
                actions ={actions}
                title ={`Part list table of ${partMaterialTitle}` }
            >
            </CustomTable>
        </>
    );
}
export {MaterialTable};