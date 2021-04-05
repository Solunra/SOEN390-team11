import {makeStyles} from "@material-ui/core";
import { Button } from "@material-ui/core";
import { useEffect, useState } from "react";
import {CustomTable} from "../Utils/CustomTable";
import request from "superagent";
import BuildPath from "../RequestBuilder";


const useStyles = makeStyles(theme => ({
    container: {
        marginTop: '60px'
    },
    rootGrid: {
        flexGrow: 1,
    },
    paper: {
        padding: theme.spacing(2),
        textAlign: 'center',
        color: theme.palette.text.secondary,
    },
}));


const Logs = () => {
    const [logs, setLogs] = useState([]);
    const [re_render, setRe_render] = useState(false);
    const classes = useStyles();
    const columns = [
        { title: 'Time', field: 'time' },
        { title: 'Type', field: 'type' },
        { title: 'Message', field: 'message' }
    ]

    const getLogs = () => {
        request.get(BuildPath('/log'))
            .set('Authorization', localStorage.getItem("Authorization"))
            .set('Accept', 'application/json')
            .then(res => {
                if (res.status === 200)
                {
                    setLogs(res.body);
                }
            })
            .catch(err => {
                console.log(err);
            });
        };

    useEffect(() => {
        getLogs();
    }, [re_render]);
    
    return(
        <div>
            <CustomTable
                    data={logs}
                    columns = {columns}
                    title = {`Logs`}
                >
            </CustomTable>
        </div>
    )
}

export default Logs;