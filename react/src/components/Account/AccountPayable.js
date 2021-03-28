import React from 'react';
import {CustomTable} from "../Utils/CustomTable";
const AccountPayable = (props) => {
    const {payableList} = props;
    const columns = [
        { title: 'Purchase Date', field: 'orderDateTime' },
        { title: 'Delivery Date', field: 'deliveryDateTime' },
        { title: 'Raw material name', field: 'rawname' },
        { title: 'Cost', field: 'amount' },
        { title: 'Username', field: 'username' },
    ];
    console.log(payableList);


    return (
        <>
            <CustomTable
                data={payableList}
                columns={columns}
                title={`Account Payable Table`}
            >
            </CustomTable>
        </>
    );
}
export {AccountPayable};