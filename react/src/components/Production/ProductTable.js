import React from "react";
import request from "superagent";
import BuildPath from "../RequestBuilder";
import { CustomTable } from "../Utils/CustomTable";
import EditIcon from "@material-ui/icons/Edit";
import AddCircleRoundedIcon from "@material-ui/icons/AddCircleRounded";
import DeleteIcon from "@material-ui/icons/Delete";
import ArrowForwardIcon from "@material-ui/icons/ArrowForward";
import BuildIcon from "@material-ui/icons/Build";
import { Grid, makeStyles } from "@material-ui/core";
import { useState, useEffect } from "react";

const ProductTable = (props) => {
    const {
        rows,
        re_render,
        setRe_render,
        setOpen,
        setData,
        setPartTable,
        setErrMessage,
        setProductPartTitle,
    } = props;
    const [message, setMessage] = useState("");
    const columns = [
        // { title: 'Id', field: 'productid' },
        { title: "Bike Name", field: "name" },
        { title: "Type", field: "type" },
        { title: "Frame Size", field: "size" },
        { title: "Color", field: "color" },
        { title: "Finish", field: "finish" },
        { title: "Grade", field: "grade" },
    ];
    const actions = [
        {
            icon: () => {
                return <EditIcon />;
            },
            export: false,
            onClick: (event, rowData) => {
                handleEdit(rowData);
            },
        },
        {
            icon: () => {
                return <AddCircleRoundedIcon />;
            },
            position: "toolbar",
            export: false,
            onClick: () => {
                handleAdd();
            },
        },
        {
            icon: () => {
                return <DeleteIcon />;
            },
            export: false,
            onClick: (event, rowData) => {
                handleDelete(rowData);
            },
        },
        {
            icon: () => {
                return <ArrowForwardIcon />;
            },
            export: false,
            onClick: (event, rowData) => {
                setPartTable(true);
                setProductPartTitle(rowData["name"]);
            },
        },
        {
            icon: () => {
                return <BuildIcon />;
            },
            export: false,
            onClick: (event, rowData) => {
                handleStart(rowData);
            },
        },
    ];
    const handleEdit = (row) => {
        setData(row);
        setOpen(true);
    };
    const handleAdd = () => {
        setData({});
        setOpen(true);
    };
    const handleDelete = (row) => {
        request
            .delete(BuildPath("/product/delete/" + row["productid"]))
            .set("Authorization", localStorage.getItem("Authorization"))
            .set("Accept", "application/json")
            .then((res) => {
                if (res.status === 200) {
                    setRe_render(!re_render);
                }
            })
            .catch((err) => {
                console.log(err);
            });
    };
    const handleStart = (row) => {
        request
            .post(BuildPath("/machinery/product/" + row["productid"]))
            .set("Authorization", localStorage.getItem("Authorization"))
            .set("Accept", "application/json")
            .then((res) => {
                console.log(res.body);
                if (res.body === "Success") {
                    setMessage("success add product to machine");
                    setTimeout(() => {
                        setMessage("");
                    }, 3000);
                }
            })
            .catch((err) => {
                console.log(err);
            });
    };

    return (
        <div>
            <Grid>
                <Grid item xs={12}>
                    <div style={{ color: "blue" }}>{message}</div>
                </Grid>
            </Grid>
            <CustomTable
                data={rows}
                columns={columns}
                handleDelete={handleDelete}
                handleEdit={handleEdit}
                handleAdd={handleAdd}
                actions={actions}
                title={`Products Table`}
            ></CustomTable>
        </div>
    );
};
export { ProductTable };
