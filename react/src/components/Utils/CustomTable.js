import MaterialTable , {MTableToolbar, MTablePagination} from "material-table";
import {Box, makeStyles} from "@material-ui/core";

const useStyles = makeStyles({
    rootTable: {
        width: '100%',
        height: '100%',
    },

});
const CustomTable=(props) =>{
    const {data, columns,actions,title} = props;
    const classes = useStyles();

    return (
        <div className={classes.root}>
            <MaterialTable
                title={title}
                columns={columns}
                data={data}
                options={{
                    exportButton: true,
                    sorting: true,
                    searching:true,
                    filtering: true,
                    actionsColumnIndex: -1,
                    actionsCellStyle:  { marginTop: "40px", display: "flex", justifyContent: "center",alignContent: "center", alignItems:"center"},
                }}
                actions={actions}
                components={{
                    Toolbar: (toolbarProps) => (
                        <Box
                             paddingTop="30px"
                              marginTop="30px"
                             >
                            <MTableToolbar {...toolbarProps} />
                        </Box>
                    ),
                    Pagination: (paginationProps) => (
                        <Box paddingTop="30px">
                            <MTablePagination {...paginationProps} />
                        </Box>
                    )
                }}

            />
        </div>
    )
}
export {CustomTable};
