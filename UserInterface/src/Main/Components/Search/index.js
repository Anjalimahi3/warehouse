import React, { Fragment, useRef, useState } from "react";
import Style from "./index.module.scss";
import ArrowDropDownIcon from "@mui/icons-material/ArrowDropDown";
const Search = ({ rowData, Headers, setSearch }) => {
  const initialHeader = Headers[0];
  const [advanceSearch, setAdvance] = useState(null); 
  const [searchOption, setSearchOption] = useState(null);
  const onApplyFilter = (e) => {
    if (rowData.length > 0) {
      const searchResult = [...rowData].filter((current) =>
        current[advanceSearch? advanceSearch.key : initialHeader?.key]
          .toString()
          .toLowerCase()
          .includes(e.target.value.toLowerCase())
      );
      setSearch(searchResult.splice(0,20))
      setSearchOption(e.target.value)
    }
    if (!e.target.value) {
      setAdvance(null)
      setSearch(null)
    }
  };

  const selectSearchCategory=(loop)=>{
    setAdvance(loop);
    if (rowData.length > 0 && searchOption) {
      const searchResult = [...rowData].filter((current) =>
        current[loop?.key]
          .toString()
          .toLowerCase()
          .includes(searchOption.toLowerCase())
      );
      setSearch(searchResult.splice(0,20))
    }
  }
  return (
    <div
      className={`d-flex p-3 w-50 justify-content-end ${Style.searchDropdown}`}
    >
      <input
        className={Style.input}
        value={searchOption}
        onChange={onApplyFilter}
        name="filter"
        placeholder={`Enter the ${advanceSearch ? advanceSearch.columnName : initialHeader.columnName}`}
      />
      <div className="dropdown">
        <span
          class="dropdown-toggle"
          id="dropdownMenuButton1"
          data-bs-toggle="dropdown"
          aria-expanded="false"
        ><ArrowDropDownIcon/></span>
        <ul className={`dropdown-menu ${Style.dropdownMenu}`} aria-labelledby="dropdownMenuButton1">
          {Headers.map((loop, key) => {
            return (
              <div key={key} onClick={()=>selectSearchCategory(loop)} className={Style.searchDropdownList}>{loop.columnName}</div>
            );
          })}
        </ul>
      </div>
    </div>
  );
};
export default React.memo(Search);
