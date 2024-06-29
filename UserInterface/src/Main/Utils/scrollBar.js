import React, { useState, useEffect, Fragment } from "react";
import Style from "./utils.module.scss";
import { getWindowDimensions } from "../../Hooks/windowDimensions";

const Scrollbar = ({ seperateVal, children }) => {
  const [windowDimensions, setWindowDimensions] = useState(
    getWindowDimensions()
  );
  useEffect(() => {
    const handleResize=()=> setWindowDimensions(getWindowDimensions());
    window.addEventListener("resize", handleResize);
    return () => window.removeEventListener("resize", handleResize);
  }, []);

  const heightvalue = windowDimensions.height - (windowDimensions.height / 100) * (seperateVal ? seperateVal : 20);
  return (
    <Fragment>
      <span className={`w-100 ${Style.scrollbarWrap}`} style={{ height: `${heightvalue}px`}}>
        {children}
      </span>
    </Fragment>
  );
};
export default Scrollbar;
