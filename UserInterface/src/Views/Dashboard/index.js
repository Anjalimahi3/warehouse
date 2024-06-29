import React, { Fragment, useState } from "react";
import Header from "../../Main/Header/Header";
import Search from "../../Main/Components/Search/index";
import Sidebar from "../../Main/Sidebar/sidebar";
import Table from "./dashboardTable";
import Style from '../view.module.scss';
import { useSelector } from "react-redux";

const Dashboard = () => { 
    const selector = useSelector(state=>state.profilePopup)
    return (
        <Fragment>
            <div className={`${Style.outerContainer}`}>
               
            <Header/>
                <div className={`${Style.nestedContainer} ${selector && "pointerEvents"}`}>  
                    <Sidebar/>
                    <div className="w-100 ms-4  me-1" style={{background:'#F4F6FA', borderRadius:'8px'}}>
                    <Table/>
                    </div>
                </div>
            </div>
        </Fragment>
    )
}
export default Dashboard;