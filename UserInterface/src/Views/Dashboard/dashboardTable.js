import { toast } from "react-toastify";
import Style from "../view.module.scss";
import { Heading } from "./HeadersJson";
import Search from "../../Main/Components/Search";
import { axiosGET } from "../../Main/Utils/Axios";
import TableList from "../../Main/Utils/Table/table";
import React, { Fragment, useEffect, useState } from "react";
import TableResponsive from "../../Main/Utils/Table/TablesResponsive";
import { getWindowDimensions } from "../../Hooks/windowDimensions";
import { axiosPOST, axiosPUT, axiosDELETE } from "../../Main/Utils/Axios";
import ExpandCircleDownIcon from "@mui/icons-material/ExpandCircleDown";
import { APISortingOrder } from "../../Main/Utils/Table/sortingOrder";
import { ToastSuccess } from "../../Main/Utils/Toast";

const DashboardTable = () => {
  const { width } = getWindowDimensions();
  const [Initialcount, setInitialcount] = useState(20);
  const [List, setList] = useState([]);
  const [values, setvalues] = useState(List);
  const [valueContain, setValueContain] = useState(false);
  const [searchResult, setSearch] = useState(null);

  const FetchRecords = async () => {
    const response = await axiosGET(process.env.REACT_APP_PURCHASE_HISTORY);
    if (response.status === 200) setList(APISortingOrder(response.data));
  };
  useEffect(() => {FetchRecords()}, []);
  useEffect(() => {
    if(!searchResult) setvalues(List.slice(0, Initialcount))
    List.length >= Initialcount ? setValueContain(true) : setValueContain(false)
  }, [List, Initialcount, searchResult]);
  const AddPurchase = () => {
    const InitialValue = {
      phId: "Purchase ID",
      vendorName: "Purchase Inventory",
      productName:"Product Name",
      unit:"Unit",
      qty: "Quantity",
      pricePerUnit: "Price/Unit",
      totalPrice: "Total Price",
      purchaseDateTime: "Purchase Date",
      addSelection: "New Purchase",
    };
    setList([InitialValue, ...List])
  };
  const PostChanges = async (argVal) => {
    const senderData = {
      piId: argVal.unit,
      qty: argVal.qty,
      pricePerUnit: argVal.pricePerUnit,
      totalPrice: argVal.totalPrice,
    };
    if (argVal.phId !== "Purchase ID") {
      const response = await axiosPUT(
        `${process.env.REACT_APP_PURCHASE_HISTORY}/${argVal.phId}`,
        senderData
      );
      if (response.status === 200) {
        ToastSuccess("Data Edited Sucessfully")
        FetchRecords();
      }
    } else {
      const response = await axiosPOST(
        `${process.env.REACT_APP_PURCHASE_HISTORY}`,
        senderData
      );
      if (response.status === 200) {
        ToastSuccess("Data Added Sucessfully")
        FetchRecords();
      }
      return response;
    }
  };
  const DeleteRecords = async (argVal) => {
    if(argVal?.phId !== "Purchase ID"){
      const response = await axiosDELETE(`${process.env.REACT_APP_PURCHASE_HISTORY}/${argVal.phId}`);
      response.status === 200 && FetchRecords();
      return response;
    } else{
      List.shift();
      setList([...List]);
    }
    
  };
  return (
    <Fragment>
      <div className={`d-flex w-100 justify-content-end align-items-center`}>
        <button className={Style.AddpurchaseButton} onClick={AddPurchase}>
          Add Purchase
        </button>
        <Search setSearch={setSearch}
        rowData={List} Headers={Heading} />
      </div>
      {width > 768 ? (
        <TableList
          rowData={searchResult? searchResult : values}
          scrollHeight={38}
          Headers={Heading}
          PostChanges={PostChanges}
          DeleteRecords={DeleteRecords}
        />
      ) : (
        <TableResponsive rowData={values} scrollHeight={38} Headers={Heading} />
      )}
      {!searchResult && valueContain &&  <button
        className={`${Style.AddpurchaseButton} d-flex m-auto mt-2`}
        onClick={() => setInitialcount(Initialcount + 20)}
      >
        <div className="d-flex align-items-center">
          <span className="pe-2">Load More</span> <ExpandCircleDownIcon />
        </div>
      </button>}
     
    </Fragment>
  );
};
export default DashboardTable;
