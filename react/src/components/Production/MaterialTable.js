import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import CloseIcon from "@material-ui/icons/Close";

const useStyles = makeStyles({
    table: {
        minWidth: 650,
    },
});
const MaterialTable = (props)=> {
    const {rows, setMaterialTable} = props;
    const classes = useStyles();
    return (
        <TableContainer component={Paper}>
            <CloseIcon onClick={()=>{setMaterialTable(false);}}/>
            <Table className={classes.table} aria-label="simple table">
                <TableHead>
                    <TableRow>
                        <TableCell align="center">Material</TableCell>
                        <TableCell align="center">Quantity</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {rows.map((row)=> (
                        <TableRow >
                            <TableCell align="center" >{row.materialname}
                            </TableCell>
                            <TableCell align="center" >{row.materialQuantity}
                            </TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </TableContainer>
    );
}
export {MaterialTable};