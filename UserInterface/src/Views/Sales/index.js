import React, { Fragment, useState } from "react";
import Header from "../../Main/Header/Header";
import Search from "../../Main/Components/Search/index";
import Sidebar from "../../Main/Sidebar/sidebar";
import SalesRecords from "./SalesList";
import Style from '../view.module.scss';

const SalesList = () => {
    return (
        <Fragment>
            <div className={Style.outerContainer}>     
            <Header/>
                <div className={Style.nestedContainer}>  
                    <Sidebar/>
                    <div className="w-100 ms-4  me-1" style={{background:'#F4F6FA', borderRadius:'8px'}}>
                    <SalesRecords/>
                    </div>
                </div>
            </div>
        </Fragment>
    )
}
export default SalesList;