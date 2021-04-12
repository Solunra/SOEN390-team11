import { Grid, makeStyles } from "@material-ui/core";
import {Bar, Pie} from 'react-chartjs-2';
import {randomColor} from "randomcolor";
const useStyles = makeStyles((theme) => ({
    pie: {
        width: "100%",
        height: "100%",
    },
}));

const DataPie= ({dataJson})=>{
    const classes = useStyles();
    const backgroundColorList = dataJson.map((_)=>randomColor());
    const data = {
        labels: dataJson.map(({month})=>[month]),
        datasets: [
            {
                label: 'My First dataset',
                backgroundColor: backgroundColorList,
                borderWidth: 1,
                data: dataJson.map(({amount})=>[amount]),
                hoverOffset: 4
            }
        ]
    };
    return (
        <>
            <div className={classes.pie}>
                <h2>Pie chart </h2>
                <Pie
                    data={data}
                    width={100}
                    height={50}
                />
            </div>
        </>
    );

};
export {DataPie};