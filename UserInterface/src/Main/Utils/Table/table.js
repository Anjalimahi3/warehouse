import React, { Fragment, useEffect, useLayoutEffect, useState } from "react";
import Style from "./table.module.scss";
import Scrollbar from "../../Utils/scrollBar";
import ArrowDropUpIcon from "@mui/icons-material/ArrowDropUp";
import ArrowDropDownIcon from "@mui/icons-material/ArrowDropDown";
import TableRow from "./tableRow";
import { sortingOrder } from "./sortingOrder";
import Spinners from "../Spinner";

const Table = ({ rowData, scrollHeight, Headers,PostChanges, DeleteRecords, FetchRecords}) => {
  const [Listvalues, setValues] = useState([]);
  const [sortingKey, setSorting] = useState();
  
  useEffect(()=>{setValues(rowData)}, [rowData])
  return (
    <Fragment>
      <table className="w-100">
        <thead>
          <tr>
            {Headers.map((loop, key) => {
                return (
                  <th key={key} className="ps-2">
                    <span className={Style.columnName}>{loop.columnName}</span>
                    <span
                      onClick={() => sortingOrder(loop, Listvalues, setSorting, setValues, sortingKey)}
                      className={Style.sortingOrder}
                    >
                      {sortingKey === loop ? (
                        <ArrowDropDownIcon fontSize="large" color="secondary" />
                      ) : (
                        <ArrowDropUpIcon fontSize="large" color="secondary" />
                      )}
                    </span>
                  </th>
                );
              })}
          </tr>
        </thead>
        <Scrollbar seperateVal={scrollHeight}>
          <tbody style={{position:'relative'}}>
            {Listvalues.length > 0 ? (
              Listvalues.map((val, key) => {
                return (
                  <Fragment>
                    <TableRow Headers={Headers} LoopingVal={val} key={key}
                     PostChanges={PostChanges} DeleteRecords={DeleteRecords}
                     FetchRecords={FetchRecords}
                    />
                  </Fragment>
                );
              })
            ) : <Spinners/>
            }
          </tbody>
        </Scrollbar>
      </table>
    </Fragment>
  );
};
export default React.memo(Table);
