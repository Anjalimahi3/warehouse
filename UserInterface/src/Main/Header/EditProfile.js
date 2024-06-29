import React, { Fragment } from "react";
import Style from './index.module.scss';

const EditProfile = () => {
  const UserInput=({label, name, placeholder})=>{
    return(
      <div>
        <label className={`d-block ${Style.headingLabel}`}>{label}&nbsp;:</label>
        <input className={Style.input} placeholder={placeholder} name={name}/>
      </div>
    )
  };

const RoleOption=({label})=>{
  return(
    <div>
        <label  className={`d-block ${Style.headingLabel}`}>{label}</label>
        <select className={Style.input}>
          <option>Select</option>
          <option>Admin</option>
          <option>Sales</option>
        </select>
      </div>
  )
};


  return (
    <div className="w-75">
        <form className={Style.profileOuterWrapper}>
          <h2 className={Style.profileHeading}>Profile Details</h2>
            <UserInput label={"Employee Name"} name={"Employee"} placeholder={"Employee Name"}/>
                <div className={Style.buttonWrapper}>
              <button className={Style.button}>Save</button>
              <button className={Style.button} style={{background:'#585c60'}}>Cancel</button>
            </div>
        </form>
    </div>
  );
};
export default EditProfile;
