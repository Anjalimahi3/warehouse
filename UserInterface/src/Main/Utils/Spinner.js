import React from "react";

const Spinners = () => {
  return (
    <div className="w-100 justify-content-center d-flex mt-5">
      <div
        className="spinner-border text-primary"
        style={{ width: "3rem", height: "3rem" }}
        role="status"
      >
        <span className="sr-only"></span>
      </div>
    </div>
  );
};
export default Spinners;
