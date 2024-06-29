import React, { Fragment, useEffect, useState } from "react";
import TableList from "../../Main/Utils/Table/table";
import TableResponsive from "../../Main/Utils/Table/TablesResponsive";
import { getWindowDimensions } from "../../Hooks/windowDimensions";
import Search from "../../Main/Components/Search";
import Style from "../view.module.scss";
import { axiosGET } from "../../Main/Utils/Axios";
import Spinners from '../../Main/Utils/Spinner';
import { Heading } from "./HeadersJson";
import { axiosPOST, axiosPUT, axiosDELETE } from "../../Main/Utils/Axios";
import { ToastSuccess } from "../../Main/Utils/Toast";
const SalesList = () => {
  const { width, height } = getWindowDimensions();
  const [List, setList] = useState([]);
  const [values, setvalues] = useState(List);
  const [valueContain, setValueContain] = useState(false);
  const [searching, setSearching] = useState(false);
  const [searchOption, setSearchOption] = useState(null);

  const FetchRecords = async () => {
    const response = await axiosGET(process.env.REACT_APP_SALESLIST);
    if(response.status === 200){
      setList(response.data);
    }
    console.log(response, "responseDetails")
    response && setValueContain(true)
  };
  useEffect(() => {FetchRecords()}, []);
  useEffect(() => {setvalues(List)}, [List]);

  const AddSales = () => {
    const InitialValue = {
      shId: 'Sales ID',
      vendorName: "Sales Inventory",
      productName:"Product Name",
      unit:"Unit",
      qty: 'Quantity',
      pricePerUnit: 'Price/Unit',
      totalPrice: 'Total Price',
      saleDateTime: 'sales Date',
      addSelection:'New sales'
    };
    setList([InitialValue, ...values]);
  };

  const PostChanges=async(argVal)=>{
    console.log(argVal.unit)
      const senderData = {
        "piId": argVal.unit,
        "qty": argVal.qty,
        "pricePerUnit": argVal.pricePerUnit,
        "totalPrice": argVal.totalPrice
      }
      if(argVal.shId !== 'Sales ID'){
        const response = await axiosPUT(`${process.env.REACT_APP_SALESLIST}/${argVal.shId}`, senderData);
        if(response.status === 200){
          FetchRecords()
        }
      } else{
        const response = await axiosPOST(`${process.env.REACT_APP_SALESLIST}`, senderData);
        if(response.status === 200) {
          FetchRecords()}
        return response;
      }
  }

  const DeleteRecords=async(argVal)=>{
    const response = await axiosDELETE(`${process.env.REACT_APP_SALESLIST}/${argVal.shId}`);
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
          <button className={Style.AddpurchaseButton} onClick={AddSales}>Add Sales</button>
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
export default SalesList;
