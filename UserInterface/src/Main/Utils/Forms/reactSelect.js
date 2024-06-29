import React, { useState, useCallback, useEffect, Fragment } from "react";
import Style from "./Forms.module.scss";
import "./Forms.module.scss";
import Box from '@mui/material/Box';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';


const ReactSelect = ({ onHandleChange, optionVal, defaultValue, name, PiIdValue }) => {
  console.log(defaultValue.length >0, "defaultvalue")
  return (
      <FormControl fullWidth className={Style.labelWrapper}>
        <Select
        labelId="demo-controlled-open-select-label"
          name={name}
          defaultValue={"Select"}
          onChange={(e) => onHandleChange(e)}
          placeholder="Select"
        >
             <MenuItem value="" selected>
            <em>Select</em>
          </MenuItem>
            {optionVal && optionVal.map((loop,key)=>{
                return(
                  <MenuItem key={key}
                  value={PiIdValue ? PiIdValue[key] : loop}>{loop}</MenuItem>
                )
              })}
   
        </Select>
      </FormControl>
  );
};

export default ReactSelect;
