import React, { Fragment, useState } from "react";
import { useLocation } from "react-router-dom";
import Style from "./sidebar.module.scss";
import { NavLink } from "react-router-dom";
import StoreIcon from "@mui/icons-material/Store";
import ShoppingCartIcon from "@mui/icons-material/ShoppingCart";
import InventoryIcon from "@mui/icons-material/Inventory";
import BubbleChartIcon from "@mui/icons-material/BubbleChart";
import ChevronRightIcon from "@mui/icons-material/ChevronRight";
import ChevronLeftIcon from "@mui/icons-material/ChevronLeft";
import { Tooltip as ReactTooltip } from "react-tooltip";
import "react-tooltip/dist/react-tooltip.css";
import { useSelector, useDispatch } from "react-redux";
import { SIDEBAR } from "../../Store/action";

const Sidebar = () => {
  const location = useLocation();
  const dispatch = useDispatch();
  const sideBarCondition = useSelector((state) => state.sidebarOpen);

  const sidebarData = [
    {
      Icon: <StoreIcon />,
      Name: "Purchase History",
      Url: "/dashboard",
    },
    {
      Icon: <ShoppingCartIcon />,
      Name: "Sales History",
      Url: "/salesdetails",
    },
    {
      Icon: <InventoryIcon />,
      Name: "Inventory",
      Url: "/inventory",
    },
    {
      Icon: <BubbleChartIcon />,
      Name: "Summary",
      Url: "/track",
    },
  ];

  return (
    <Fragment>
      <div className={`d-block ${Style.sidebar_Wrapper}`}>
        <div className={Style.toggle}>
          {sideBarCondition ? (
            <span onClick={() => dispatch({ type: SIDEBAR, Payload: false })}>
              <ChevronLeftIcon />
            </span>
          ) : (
            <span onClick={() => dispatch({ type: SIDEBAR, Payload: true })}>
              <ChevronRightIcon />
            </span>
          )}
        </div>
        {sidebarData.map((item, key) => {
          return (
            <div key={key}>
              <NavLink
                exact={true}
                id={item.Name}
                to={`${item.Url}`}
                className={`d-flex ${sideBarCondition ? Style.sidebarOpen : Style.sidebarClose
                  }
                ${location.pathname === item.Url
                    ? Style.activeTab
                    : sideBarCondition
                      ? Style.sidebarOptions
                      : Style.SideBarClose
                  }`}

                data-tip="Copied"
                data-event="click"
              >
                <span className={`${sideBarCondition && "pe-2"}`}>{item.Icon}</span>
                {sideBarCondition && <span>{item.Name}</span>}
                {!sideBarCondition && (
                  <ReactTooltip className={Style.ToogleTip} anchorId={item.Name} place="right"
                    content={item.Name}
                  />
                )}
              </NavLink>
            </div>
          );
        })}
      </div>
    </Fragment>
  );
};
export default React.memo(Sidebar);
