import React, { Fragment } from 'react';
import Header from '../Main/Header/Header';
import Sidebar from '../Main/Sidebar/sidebar';
import EditProfile from '../Main/Header/EditProfile';
import Style from '../Views/view.module.scss';

const EditProfilePage = () => {
    return (
        <Fragment>
            <div className={Style.outerContainer}>
            <Header/>
                <div className={Style.nestedContainer} >  
                    <Sidebar/>
                    <EditProfile/>
                </div>
            </div>
        </Fragment>
    )
}
export default EditProfilePage;