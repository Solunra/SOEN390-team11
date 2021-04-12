import React, { useState } from "react";
import { CustomTable } from "../Utils/CustomTable";
import BuildPath from "../RequestBuilder";
import request from "superagent";
import { FormatLineSpacingRounded } from "@material-ui/icons";
import CloseIcon from "@material-ui/icons/Close";

const AccountReceivable = (props) => {
    const { receivableList } = props;
    const [displayList, setDisplayList] = useState(false);
    const [productList, setProductList] = useState([]);
    const columnsAR = [
        { title: "Invoice Date", field: "invoiceDate" },
        { title: "Customer", field: "username" },
        { title: "Balance Due", field: "amount" },
    ];
    const columnsPL = [
        { title: "Bike Name", field: "name" },
        { title: "Frame Size", field: "size" },
        { title: "Color", field: "color" },
        { title: "Finish", field: "finish" },
        { title: "Price", field: "price" },
        { title: "Status", field: "status" },
    ];
    const actionsPL = [
        {
            icon: () => {
                return <CloseIcon />;
            },
            position: "toolbar",
            export: false,
            onClick: (event, rowData) => {
                setDisplayList(false);
            },
        },
    ];
    const getProductList = (rowData) => {
        request
            .get(BuildPath("/customer/purchase/" + rowData["invoiceID"]))
            .set("Authorization", localStorage.getItem("Authorization"))
            .set("Accept", "application/json")
            .then((res) => {
                if (res.status === 200) {
                    setProductList(res.body);
                }
            })
            .catch((err) => {
                console.log(err);
            });
    };

    const actionsAR = [
        {
            icon: () => {
                return <FormatLineSpacingRounded />;
            },
            export: false,
            onClick: (event, rowData) => {
                setDisplayList(true);
                getProductList(rowData);
            },
        },
    ];

    return (
        <div>
            <CustomTable
                data={receivableList}
                columns={columnsAR}
                actions={actionsAR}
                title={`Account Receivable Table`}
            ></CustomTable>
            {displayList && (
                <CustomTable
                    data={productList}
                    columns={columnsPL}
                    actions={actionsPL}
                    title={`List of product within invoices`}
                />
            )}
        </div>
    );
};
export { AccountReceivable };
