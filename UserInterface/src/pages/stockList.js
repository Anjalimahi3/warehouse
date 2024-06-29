import React, { Fragment } from "react";
import Header from "../Main/Header/Header";
import Sidebar from "../Main/Sidebar/sidebar";
import Style from '../Views/view.module.scss';
const StockList = () => {
    return (
        <Fragment>
            <div className={Style.outerContainer}>
            <Header/>
                <div className={Style.nestedContainer}>  
                    <Sidebar/>
                    <div className="w-100 mx-4" style={{background:'#F4F6FA', borderRadius:'8px'}}>
                        <h2 className="ms-5">Stock details</h2>
                    </div>
                </div>
            </div>
        </Fragment>
    )
}
export default StockList;