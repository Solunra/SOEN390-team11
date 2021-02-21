import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Paper from '@material-ui/core/Paper';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TablePagination from '@material-ui/core/TablePagination';
import TableRow from '@material-ui/core/TableRow';
import DeleteIcon from '@material-ui/icons/Delete';
import IconButton from '@material-ui/core/IconButton';
import EditIcon from '@material-ui/icons/Edit';
import request from "superagent";
import BuildPath from "../RequestBuilder";

const columns = [
    { id: 'name', label: 'Bike Name' ,minWidth: 170 },
    { id: 'type', label: 'Type' ,minWidth: 100,align: 'center'},
    { id: 'size', label: 'Frame Size',minWidth: 100 ,align: 'center'},
    { id: 'color', label: 'Color' ,minWidth: 100,align: 'center'},
    { id: 'finish',label: 'Finish',minWidth: 100 ,align: 'center'},
    { id: 'grade', label: 'Grade',minWidth: 100,align: 'center' },
];


const useStyles = makeStyles({
    rootTable: {
        width: '100%',
        height: '100%',
    },

});

const ProductTable = (props) => {
    const {rows,re_render, setRe_render , setOpen,setData} = props;
    const classes = useStyles();
    const [page, setPage] = React.useState(0);
    const [rowsPerPage, setRowsPerPage] = React.useState(10);

    const handleChangePage = (event, newPage) => {
        setPage(newPage);
    };

    const handleChangeRowsPerPage = (event) => {
        setRowsPerPage(+event.target.value);
        setPage(0);
    };
    const handleEdit = (row) =>{
        setData(row);
        setOpen(true);
    }
    const handleDelete = (row) =>{
        request
            .delete(BuildPath("/product/delete/"+row['productid']))
            .set('Authorization', localStorage.getItem("Authorization"))
            .set('Accept', 'application/json')
            .then(res => {
                if (res.status === 200) {
                    setRe_render(!re_render);
                }
            })
            .catch(err => {
                console.log(err);
            });
    }

    return (
            <Paper className={classes.rootTable}>
                <TableContainer >
                    <Table stickyHeader aria-label="sticky table">
                        <TableHead>
                            <TableRow>
                                {columns.map((column) => (
                                    <TableCell
                                        key={column.id}
                                        align={column.align}
                                        style={{minWidth: column.minWidth}}
                                    >
                                        {column.label}
                                    </TableCell>
                                ))}
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {rows.slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage).map((row) => {
                                return (
                                    <TableRow hover role="checkbox" tabIndex={-1} key={row.code}>
                                        {columns.map((column) => {
                                            const value = row[column.id];
                                            return (
                                                <TableCell key={column.id} align={column.align}>
                                                    {column.format && typeof value === 'number' ? column.format(value) : value}
                                                </TableCell>
                                            );
                                        })}
                                        <TableCell >
                                            <IconButton onClick={()=>{handleEdit(row)}}>
                                                <EditIcon></EditIcon>
                                            </IconButton>
                                        </TableCell>
                                        <TableCell>
                                            <IconButton onClick={()=>{handleDelete(row)}}>
                                                <DeleteIcon></DeleteIcon>
                                            </IconButton>
                                        </TableCell>
                                    </TableRow>
                                );
                            })}
                        </TableBody>
                    </Table>
                </TableContainer>
                <TablePagination
                    rowsPerPageOptions={[10, 25, 100]}
                    component="div"
                    count={rows.length}
                    rowsPerPage={rowsPerPage}
                    page={page}
                    onChangePage={handleChangePage}
                    onChangeRowsPerPage={handleChangeRowsPerPage}
                />
            </Paper>
    );


}
export {ProductTable};
