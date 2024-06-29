import React, { Fragment, useState } from "react";
import Header from "../../Main/Header/Header";
import Sidebar from "../../Main/Sidebar/sidebar";
import Style from '../../Views/view.module.scss';
import { useSelector } from "react-redux";
import Inventory from '../../Main/Components/Inventory/index';

const InventoryView = () => { 
    const selector = useSelector(state=>state.profilePopup)
    return (
        <Fragment>
            <div className={`${Style.outerContainer}`}>
            <Header/>
                <div className={`${Style.nestedContainer} ${selector && "pointerEvents"}`}>  
                    <Sidebar/>
                    <div className="w-100 ms-4  me-1" style={{background:'#F4F6FA', borderRadius:'8px'}}>
                    <Inventory/>
                    </div>
                </div>
            </div>
        </Fragment>
    )
}
export default InventoryView;