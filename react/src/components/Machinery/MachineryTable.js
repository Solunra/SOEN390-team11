import {
    PlayCircleFilled,
    PauseCircleFilled,
    CancelOutlined,
} from "@material-ui/icons";
import React, { useEffect, useState } from "react";
import { CustomTable } from "../Utils/CustomTable";
import request from "superagent";
import BuildPath from "../RequestBuilder";
import { Grid } from "@material-ui/core";

// Return a machine table that will display the characteristics of a machine (ID, name, product)
const MachineryTable = (props) => {
    const [data, setData] = useState({});
    const [machineryList, setMachineryList] = useState([]);
    const [re_render, setRe_render] = useState(true);
    const [error, setError] = useState("");
    const getMachinery = () => {
        request
            .get(BuildPath("/machinery/"))
            .set("Authorization", localStorage.getItem("Authorization"))
            .set("Accept", "application/json")
            .then((res) => {
                if (res.status === 200) {
                    if (
                        JSON.stringify(machineryList) !==
                        JSON.stringify(res.body)
                    ) {
                        setMachineryList(res.body);
                    }
                }
            })
            .catch((err) => {
                console.log(err);
            });
    };
    useEffect(() => {
        getMachinery();
    }, [re_render]);

    const columns = [
        { title: "Machine Name", field: "name" },
        { title: "Status", field: "status" },
        { title: "Product name", field: "productname" },
        { title: "Timer", field: "timer" },
    ];
    setInterval(() => {
        setRe_render(!re_render);
    }, 25000);
    const actions = [
        {
            icon: () => {
                return <PlayCircleFilled />;
            },
            export: false,
            onClick: (event, rowData) => {
                handleStart(rowData);
            },
        },
        {
            icon: () => {
                return <PauseCircleFilled />;
            },
            export: false,
            onClick: (event, rowData) => {
                handlePause(rowData);
            },
        },
        {
            icon: () => {
                return <CancelOutlined />;
            },
            export: false,
            onClick: (event, rowData) => {
                handleCancel(rowData);
            },
        },
    ];
    const handleStart = (row) => {
        request
            .post(BuildPath("/machinery/" + row["macId"] + "/START"))
            .set("Authorization", localStorage.getItem("Authorization"))
            .set("Accept", "application/json")
            .then((res) => {
                if (res.status === 200) {
                    setRe_render(!re_render);
                }
            })
            .catch((err) => {
                console.log(err);
                handleError("operation start not success");
            });
    };
    const handlePause = (row) => {
        request
            .post(BuildPath("/machinery/" + row["macId"] + "/PAUSE"))
            .set("Authorization", localStorage.getItem("Authorization"))
            .set("Accept", "application/json")
            .then((res) => {
                if (res.status === 200) {
                    setRe_render(!re_render);
                }
            })
            .catch((err) => {
                console.log(err);
                handleError("operation pause not success");
            });
    };
    const handleCancel = (row) => {
        request
            .post(BuildPath("/machinery/" + row["macId"] + "/CANCEL"))
            .set("Authorization", localStorage.getItem("Authorization"))
            .set("Accept", "application/json")
            .then((res) => {
                if (res.status === 200) {
                    setRe_render(!re_render);
                }
            })
            .catch((err) => {
                console.log(err);
                handleError("operation cancel not success");
            });
    };
    const handleError = (err) => {
        setError(err);
        setTimeout(() => {
            setError("");
        }, 3000);
    };
    return (
        <>
            <CustomTable
                data={machineryList}
                columns={columns}
                actions={actions}
                title={`Machinery Table`}
            ></CustomTable>
            <Grid item xs={12}>
                <div style={{ color: "red" }}>{error}</div>
            </Grid>
        </>
    );
};

export { MachineryTable };
