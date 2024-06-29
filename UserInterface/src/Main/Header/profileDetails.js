import React, { Fragment } from "react";
import Style from "./index.module.scss";
const ProfileDetails = () => {

  const profile = {
    Name: sessionStorage.userName,
    Designation: "Admin",
    Email: sessionStorage.userEmail,
  };

  return (
    <Fragment>
     <div>
        {Object.keys(profile).map((val) => {
          return (
            <Fragment>
              <div className="d-flex px-5 pt-3 ">
                <span className={`w-50 ${Style.profileKey} d-flex`}>{val}</span>
                <span className="fw-bold me-2">:</span>
                <span className="w-75">{profile[val]}</span>
              </div>
            </Fragment>
          );
        })}
      </div>
  
    </Fragment>
  );
};
export default ProfileDetails;
