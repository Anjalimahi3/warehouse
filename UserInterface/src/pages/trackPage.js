import React, { Fragment } from "react";
import Header from "../Main/Header/Header";
import Sidebar from "../Main/Sidebar/sidebar";
import Style from '../Views/view.module.scss';
import Trackoption from "../Main/Components/Track";
const TrackPage = () => {
    return (
        <Fragment>
            <div className={Style.outerContainer}>
            <Header/>
                <div className={Style.nestedContainer}>  
                    <Sidebar/>
                    <div className="w-100 mx-4" style={{background:'#F4F6FA', borderRadius:'8px'}}>
                        <Trackoption/>
                    </div>
                </div>
            </div>
        </Fragment>
    )
}
export default TrackPage;