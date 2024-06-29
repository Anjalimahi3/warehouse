import React, { Fragment, useEffect, useState } from "react";
import CheckCircleIcon from "@mui/icons-material/CheckCircle";
import CancelIcon from "@mui/icons-material/Cancel";
import DeleteIcon from "@mui/icons-material/Delete";
import EditIcon from "@mui/icons-material/Edit";
import { useLocation } from "react-router-dom";
import TableCell from "./TableCell";
import { ToastWarn } from "../Toast";
import { selectDataValues } from "./selectionData";

const TableRow = ({ Headers, LoopingVal, PostChanges, DeleteRecords, key }) => {
  
  const [vendor, setVendor] = useState();
  const [product, setProduct] = useState();


  let location = useLocation();
  const [editclick, setEditClick] = useState(false);
  const [Records, EditRecords] = useState();
  const onHandleChange = (event) => {

    const { name, value } = event.target;
  if(name==="vendorName") setVendor(value) 
  else if(name ==="productName")setProduct(value)
    EditRecords((previousState) => {
      return {
        ...previousState,
        [name]: value,
      };
    });
  };
  useEffect(() => {
    if (LoopingVal?.addSelection) {
      setEditClick(true);
    }
    EditRecords(LoopingVal);
  }, [LoopingVal]);

  const HandlingPostChanges = () => {
    if (location.pathname === "/inventory") {
      PostChanges(Records);
      setEditClick(false);
    } else if (
      parseInt(Records.pricePerUnit) > 0 &&
      parseInt(Records.qty) > 0 
    ) {
      const priceCalc = Records.pricePerUnit * Records.qty;
      const concatTotalPrice = { ...Records, totalPrice: priceCalc };
      PostChanges(concatTotalPrice);
      setEditClick(false);
    } else {
      ToastWarn("Please Enter All The Field");
    }
  };

  return (
    <Fragment>
      <tr key={key}>
        {Headers.map((loop, IndexVal) => {
          const Loopval =
            LoopingVal[loop?.key] && LoopingVal[loop?.key].toString();
          const dateField = loop?.type === "date";
          return (
            <Fragment>
              {!editclick && Loopval && (
                <td key={IndexVal} className="pe-3">
                  {dateField ? new Date(Loopval).toLocaleString() : Loopval}
                </td>
              )}
              {editclick && Loopval && (
                <td key={IndexVal}>
                  <TableCell
                    data={loop?.type}
                    vendor={vendor}
                    product={product}
                    Loopval={Loopval}
                    loop={loop}
                    onHandleChange={onHandleChange}
                    Records={Records}
                  />
                </td>
              )}
            </Fragment>
          );
        })}
        <td>
          {editclick && (
            <span className="pointer">
              <span className="pe-3">
                <CheckCircleIcon onClick={HandlingPostChanges} />
              </span>
              <span onClick={() => setEditClick(false)}>
                <CancelIcon />
              </span>
            </span>
          )}
          {!editclick && (
            <span className="pointer">
              <span className="pe-3" onClick={() => setEditClick(true)}>
                <EditIcon />
              </span>
              <span onClick={() => DeleteRecords(LoopingVal)}>
                <DeleteIcon />
              </span>
            </span>
          )}
        </td>
      </tr>
    </Fragment>
  );
};
export default TableRow;
