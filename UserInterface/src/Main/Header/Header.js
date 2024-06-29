import React, { Fragment } from "react";
import Avatar from "@mui/material/Avatar";
import Style from "./index.module.scss";
import HeaderMenu from "./HeaderMenu";
import useComponentVisible from "../../Hooks/useComponentVisible";
import { useNavigate } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { PROFILEPOPUP } from '../../Store/action';
const Header = () => {
  const userDetail = useSelector(state=>state)
  const { ref, isComponentVisible, setIsComponentVisible } =
    useComponentVisible(false);
  const navigate = useNavigate();
  const dispatch = useDispatch();

  const ProfilePopup =()=>{
    dispatch({type:PROFILEPOPUP, Payload:!isComponentVisible})
    setIsComponentVisible(!isComponentVisible)
  }

  const clearToken=()=>{
    sessionStorage.removeItem("tokenValue");
    navigate("/signin");
  }
console.log(userDetail, "userDetail")
  return (
    <Fragment>
      <div className={`${Style.Header_wrapper} d-flex justify-content-between`}>
        <div className=" d-flex align-items-center">
          <img className={Style.logoDesign} src="/construction.png"/>
          <span className={Style.Header_title}>AGS</span>
        </div>
        <div className=" d-flex align-items-center text-center">
          <Avatar
            className="me-1"
            alt="Remy Sharp"
            src="/construction.png"
            sx={{ width: 45, height: 45 }}
          />
          <div className="dropdown">
            <div
              className="d-block dropdown-toggle pointer"
              style={{ height: "14px" }}
              id="dropdownMenuButton1"
              data-bs-toggle="dropdown"
              aria-expanded="false"
              
            >
              <div className={Style.Header_Profile}>{sessionStorage.userName}</div>
              <span className={`text-center ${Style.Header_Profile}`}>
                Admin
              </span>
            </div>
            <ul className={` dropdown-menu ${Style.listgroup_option}  ${Style.ProfileDropdown}`}>
              <li className={`pointer dropdown-item ${Style.listcontent}`} onClick={ProfilePopup}>
                Profile
              </li>
              <li
                className={`pointer dropdown-item ${Style.listcontent}`}
                onClick={clearToken}
              >
                Logout
              </li>
            </ul>
          </div>
          {isComponentVisible && <HeaderMenu ref={ref} 
          isComponentVisible={isComponentVisible}
            setIsComponentVisible={setIsComponentVisible}/>}
        </div>
      </div>
    </Fragment>
  );
};
export default Header;
