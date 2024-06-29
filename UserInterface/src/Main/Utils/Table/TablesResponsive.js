import React, { Fragment } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import style from './tableresponsive.module.scss';
import Scrollbar from "../../Utils/scrollBar";

const TableResponsive=({ rowData, scrollHeight })=>{
      const Values = (argVal)=>{
        let entries = Object.entries(argVal);
        return entries.map( ([key, val]) => {
            return (
                <div className='w-100 d-flex justify-content-between p-2' style={{borderBottom:'1px solid #fff'}}>
                    <span className='fw-bold' style={{textTransform:'uppercase'}}>{key}</span>
                    <span>{val}</span>
                </div>
            )
          });
      }
    return(
        <Fragment>
              <Scrollbar seperateVal={scrollHeight}>
            <table className={style.table}>
                {
                  rowData.map(loop=><tr className={style.tr}>{Values(loop)}</tr>)  
                }
            </table>
            </Scrollbar>
        </Fragment>
    )
}
export default TableResponsive;