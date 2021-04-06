import React, {useState} from 'react';
import {Grid} from "@material-ui/core";
import {CustomTable} from "../Utils/CustomTable";
import EditIcon from "@material-ui/icons/Edit";
import AddCircleRoundedIcon from "@material-ui/icons/AddCircleRounded";
import DeleteIcon from "@material-ui/icons/Delete";
import ShoppingBasketTwoToneIcon from '@material-ui/icons/ShoppingBasketTwoTone';
import BuildPath from "../RequestBuilder";
import request from 'superagent';

const CustomerOrder = (props) => {
    const {rows,loading, setLoading} = props;
    const [actionsReply, setActionsReply] = useState('');
    const [errMessage,setErrMessage]=useState('');
    const columns = [
        { title: 'Bike Name', field: 'name' },
        { title: 'Frame Size', field: 'size' },
        { title: 'Color', field: 'color'},
        { title: 'Finish', field: 'finish' },
        { title: 'Price', field: 'price'},
        { title: 'Status', field: 'status'},
    ];
    const actions = [
        {
            icon: () => { return <EditIcon />;},
            export: false,
            onClick: (event, rowData) => {
                console.log(rowData);
                ShipOrProduce(rowData);
            }
        },
    ];
    const ShipOrProduce=(rowData)=>{
        request
            .get(BuildPath("/customer/orderAction/"+rowData['productid']+"/"+rowData['invoiceid']))
            .set('Authorization', localStorage.getItem("Authorization"))
            .set('Accept', 'application/json')
            .then(res => {
                if (res.status === 200)
                {
                    console.log(res.body);
                    setLoading(!loading);
                    setActionsReply(res.body);
                    setTimeout(() => {
                        setActionsReply('')
                    }, 5000)
                }
            })
            .catch(err => {
                setErrMessage("action not sucess");
                setTimeout(()=>{
                    setErrMessage("")
                }, 3000);
            });
    }



    return (
        <>
            <Grid item xs={12}>
                <div style={ {color: 'blue' }}>{actionsReply}</div>
            </Grid>
            <CustomTable
                data={rows}
                columns={columns}
                actions={actions}
                title = {`Customer Order Table`}
            >
            </CustomTable>

        </>
    );
}
export {CustomerOrder};