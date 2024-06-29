import { Heading } from "./HeadersJson";
import Spinners from '../../../Main/Utils/Spinner';
import Style from "../../../Views/view.module.scss";
import { axiosGET } from "../../../Main/Utils/Axios";
import Search from "../../../Main/Components/Search";
import TableList from "../../../Main/Utils/Table/table";
import React, { Fragment, useEffect, useState } from "react";
import { getWindowDimensions } from "../../../Hooks/windowDimensions";
import TableResponsive from "../../../Main/Utils/Table/TablesResponsive";
import { axiosPOST, axiosPUT, axiosDELETE } from "../../../Main/Utils/Axios";
import { ToastSuccess } from "../../Utils/Toast";
//<ScaleLoader color="#36d7b7" />;

const DashboardTable = () => {
  const { width, height } = getWindowDimensions();
  const [List, setList] = useState([]);
  const [values, setvalues] = useState(List);
  const [valueContain, setValueContain] = useState(false);
  const [searching, setSearching] = useState(false);
  const [searchOption, setSearchOption] = useState(null);
  
 
  const FetchRecords = async () => {
    const response = await axiosGET(process.env.REACT_APP_INVENTORY);
    setList(response.data);
    response && setValueContain(true)
  };
  useEffect(() => {FetchRecords()}, []);
  useEffect(() => {setvalues(List)}, [List]);

  const AddInventory = () => {
    const InitialValue = {
      piId: 'Product Inventory',
      qty: 'Quantity',
      modifiedDate: "Modified Date",
      productName: "Product Name",
      vendorName: "Vendor Name",
      unit: "Unit",
      addSelection:'New Inventory'
    };
    setvalues([...values, InitialValue]);
  };

  const PostChanges=async(argVal)=>{
      const senderData = {
        "productName": argVal.productName,
        "qty": argVal.qty,
        "vendorName":argVal.vendorName,
        "unit":argVal.unit
      }
      if(argVal.piId !== 'Product Inventory'){
        const response = await axiosPUT(`${process.env.REACT_APP_INVENTORY}/${argVal.piId}`, senderData);
        if(response.status === 200){
          FetchRecords();
        }
      } else{
        const response = await axiosPOST(`${process.env.REACT_APP_INVENTORY}`, senderData);
        if (response.status === 200) {
          FetchRecords();
        }
        return response;
      }
  }

  const DeleteRecords=async(argVal)=>{
    console.log(argVal, "argVal")
    const response = await axiosDELETE(`${process.env.REACT_APP_INVENTORY}/${argVal.piId}`);
    if(response.status === 200){
      const findIndex = List.filter(loop=>loop.phId === argVal.phId);
      List.splice(findIndex, 1);
      setvalues([...List]);
    }
    return response;
  }


  return (
    <Fragment>
      <div className={`d-flex w-100 justify-content-end align-items-center`}>
          <button className={Style.AddpurchaseButton} onClick={AddInventory}>Add Inventory</button>
          <Search setList={setvalues} rowData={List} setSearching={setSearching} searchOption={searchOption} Headers={Heading} />
      </div>
      {!valueContain && <Spinners/>}
          {width > 768 ? (
            <TableList rowData={values} scrollHeight={38} Headers={Heading}
             PostChanges={PostChanges} 
             DeleteRecords={DeleteRecords}
             />
          ) : (
            <TableResponsive rowData={values} scrollHeight={38} Headers={Heading} />
          )}
    </Fragment>
  );
};
export default DashboardTable;
