import React, { Fragment, useEffect, useState } from "react";
import Style from "./table.module.scss";
import ReactSelect from "../Forms/reactSelect";
import {selectDataValues} from "./selectionData";

const columnName = [
  "Purchase ID",
  "Sales ID",
  "Total Price",
  "Product Inventory",
];
const TableCell = ({
  data,
  Loopval,
  loop,
  onHandleChange,
  vendor,
  product,
}) => {

  const [ListedRecords, setRecords] = useState([]);
  const RecordsList = async () => {
    const response = await selectDataValues();
    setRecords(response);
  };

  useEffect(() => {
    RecordsList();
  }, []);
  const cellInput = (inputArg) => (
    <input
      type={"text"}
      className={`${Style.input}`}
      placeholder={Loopval}
      autoFocus
      disabled={inputArg}
      name={loop.key}
      onChange={onHandleChange}
    />
  );

  switch (data) {
    case "date":
      return cellInput(true);
    case "inventory":
      const vendorList = ListedRecords.map(looping=>looping.vendorName)
      return (
        <ReactSelect
          onHandleChange={onHandleChange}
          optionVal={vendorList}
          defaultValue={""}
          PiIdValue={null}
          name={"vendorName"}
        />
      );
    case "productName":
      const ProductList = vendor ? ListedRecords.find(loopRec=>loopRec.vendorName===vendor)?.product :[];
      const products = ProductList.length>0 ? ProductList?.map(loop=>loop.productName) : []
      return (
        <ReactSelect
          onHandleChange={onHandleChange}
          optionVal={products}
          defaultValue={""}
          PiIdValue={null}
          name={"productName"}
        />
      );
    case "unit":
      const ProductListUnit = vendor && product ? ListedRecords.find(loopRec=>loopRec.vendorName===vendor)?.product :[];
      const FetchParticularUnit = ProductListUnit.length>0 ? ProductListUnit.find(loop=>loop.productName === product)?.unit : [];
      const productUnitList= FetchParticularUnit.length > 0 ? FetchParticularUnit.map(loop=>loop.unit):[];
      const PiIdValue= FetchParticularUnit.length > 0 ? FetchParticularUnit.map(loop=>loop.piId):[];
      return (
        <ReactSelect
          onHandleChange={onHandleChange}
          optionVal={productUnitList}
          PiIdValue={PiIdValue}
          defaultValue={""}
          name={"unit"}
        />
      );
    default:
      return cellInput(columnName.some((data) => data === loop.columnName));
  }
};
export default TableCell;
