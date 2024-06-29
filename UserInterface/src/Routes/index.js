import { Fragment, useEffect } from "react";
import { Routes, Route } from "react-router-dom";
import Login from "../Main/Authentication/index";
import SignUp from "../Main/Authentication/signUp";
import Forgotpassword from "../Main/Authentication/forgotPassword";
import ResetPassword from "../Main/Authentication/ResetPassword";
import { Navigate } from "react-router-dom";
import EditProfilePage from "../pages/editProfilePage";
import Dashboard from "../pages/dashboardPage";
import TrackPage from "../pages/trackPage";
import Salesdetails from "../pages/salesDetailPage";
import InventoryPage from "../pages/inventory";
import { useNavigate } from "react-router-dom";

const RoutesPath = () => {
  const navigate = useNavigate();
  useEffect(() => {
    if (!sessionStorage.tokenValue) {
      navigate("/signin");
    }
  }, []);
  return (
      <Routes>
        {sessionStorage.tokenValue ? (
          <Fragment>
            <Route exact path="dashboard" element={<Dashboard />} />
            <Route exact path="inventory" element={<InventoryPage />} />
            <Route exact path="track" element={<TrackPage />} />
            <Route exact path="salesdetails" element={<Salesdetails />} />
            <Route exact path="editprofile" element={<EditProfilePage />} />
            <Route path="*" element={<Navigate to="/dashboard" replace />} />
          </Fragment>
        ) : (
          <Fragment>
            <Route exact path="signin" element={<Login />} />
            <Route exact path="signup" element={<SignUp />} />
            <Route exact path="forgotpassword" element={<Forgotpassword />} />
            <Route exact path="resetpassword" element={<ResetPassword />} />
          </Fragment>
        )}
      </Routes>
  );
};
export default RoutesPath;
