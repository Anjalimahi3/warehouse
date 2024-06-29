import React, { Fragment } from "react";
import Avatar from "react-avatar";
import Button from "@mui/material/Button";
import CloseIcon from "@mui/icons-material/Close";
import { useNavigate } from "react-router-dom";
import useComponentVisible from "../../Hooks/useComponentVisible";
import ProfileDetails from "./profileDetails";
import Style from './index.module.scss';
import { useDispatch } from "react-redux";
import { PROFILEPOPUP } from '../../Store/action';

const HeaderMenu = ({setIsComponentVisible,isComponentVisible, ref}) => {
 const dispatch = useDispatch();
  const closePopup=()=>{
    setIsComponentVisible(!isComponentVisible)
    dispatch({type:PROFILEPOPUP, Payload:!isComponentVisible})
  }
  return (
    <Fragment>
      <div>
          <div className={Style.profileWrapper} ref={ref}>
            <div className="w-100 d-flex justify-content-end">
              <CloseIcon role="button" onClick={closePopup}/>
            </div>
            <Avatar
              className="d-flex m-auto"
              name={sessionStorage.userName}
              size="80"
              round={true}
            />
            <ProfileDetails />
            <div className="w-100 d-flex justify-content-end">
              <Button
                role="button"
                variant="contained"
                className={Style.closeButton}
                onClick={closePopup}
              >
                Close
              </Button>
            </div>
          </div>
          </div>
    </Fragment>
  );
};
export default HeaderMenu;
