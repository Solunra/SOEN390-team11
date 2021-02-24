import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import ArrowForwardIcon from '@material-ui/icons/ArrowForward';
import CloseIcon from "@material-ui/icons/Close";

const useStyles = makeStyles({
    table: {
        minWidth: 650,
    },
});


const PartTable = (props)=> {
    const {rows,setMaterialTable , getPartMaterial,setPartTable} = props;
    const classes = useStyles();
    const handleClickDetailMaterial=(id)=>{
        getPartMaterial(id);
        setMaterialTable(true);
    }
    return (
        <TableContainer component={Paper}>
            <CloseIcon onClick={()=>{setPartTable(false);setMaterialTable(false);}}/>
            <Table className={classes.table} aria-label="simple table">
                <TableHead>
                    <TableRow>
                        <TableCell align="center">Part</TableCell>
                        <TableCell>See Material</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {rows.map((row)=> (
                        <TableRow >
                            <TableCell align="center" >{row.name}
                            </TableCell>
                            <TableCell >
                                <ArrowForwardIcon fontSize="small" onClick={()=>{handleClickDetailMaterial(row.partid) }} ></ArrowForwardIcon>
                            </TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </TableContainer>
    );
}
export {PartTable};
