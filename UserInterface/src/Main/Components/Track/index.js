import React, {useState, Fragment} from "react";
import Inventory from "./Inventory";
import Style from './Inventory.module.scss';
import StockDifference from "./stockDifference";
import DataUsageIcon from '@mui/icons-material/DataUsage';
import BarChartIcon from '@mui/icons-material/BarChart';
const Trackoption = () => {

    const categoryValue = {
        beautyHygiene: '₹20000',
        Dairy_breads: '₹20000',
        cookingessentials: '₹10000',
        Snacks_branckedfoods: '₹80000'
    }

    const [StockDetail, setStockDetail] = useState(false);

  return (
    <Fragment>

      <div className='d-flex m-5'>
        <button className={` ${Style.summaryButton} d-flex align-items-center pointer fw-bold`} onClick={()=>setStockDetail(false)}> Summary
        <DataUsageIcon />
        </button>
        <button className={` ${Style.summaryButton} d-flex align-items-center pointer fw-bold ms-4`} onClick={()=>setStockDetail(true)}>Stock Track
        <BarChartIcon/>
        </button>
      </div>
{StockDetail ? <StockDifference/> :  

      <Fragment>
     <div style={{
                width: '18rem',
                margin:'15px',
                display:'flex', 
                alignItems:'center', 
                justifyContent:'center'
            }}>
      <div className={`card ${Style.trackContainer} ${Style.overAllCalc}`} >

<div className={`card ${Style.trackContainer}`} style={{
                width: '18rem',
                margin:'15px',
                display:'flex', 
                alignItems:'center', 
                justifyContent:'center'
            }}>
        <div className="card-body">
          <h5 className="card-title">Profit Percentage</h5>
          <h6 className="card-subtitle mb-2 text-muted">Card subtitle</h6>
        </div>
      </div>
        <div className="card-body">
          <h5 className="card-title">Profit Amount</h5>
          <h6 className="card-subtitle mb-2 text-muted">Card subtitle</h6>
          {Object.keys(categoryValue).map((loop,key)=>{
        return(
            <div className="d-flex justify-content-between">
                <span className="fw-bold">{loop}</span>
                <span>{categoryValue[loop]}</span>
            </div>
        )
      })}</div>
        </div>
      </div>
        <Inventory />
        </Fragment>
        }
    </Fragment>
  );
};
export default Trackoption;
