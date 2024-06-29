import axios from 'axios';
import { toast } from 'react-toastify';
import { ToastSuccess } from "../Utils/Toast";
export const axiosGET = async (data) => {
    const baseURL= process.env.REACT_APP_BASEURL;
   const concatURL = `${baseURL}${data}`
   const headers = {
    'Content-Type': 'application/json',
  }
    try {
      const res = await axios.get(concatURL,{
        headers: headers
      });
        return res
    } catch (err) {
        return err
    }
  };

  export const axiosPOST = async (URL, data) => {
    const baseURL= process.env.REACT_APP_BASEURL;
   const concatURL = `${baseURL}${URL}`
    try {
      const res = await axios.post(concatURL, data);
      ToastSuccess('Data Added Sucessfully');
        return res

    } catch (err) {
        return err
    }
  };

  export const axiosPUT = async (URL, data) => {
    const baseURL= process.env.REACT_APP_BASEURL;
   const concatURL = `${baseURL}${URL}`
    try {
      const res = await axios.put(concatURL,data);
      ToastSuccess('Data Edited Sucessfully');
        return res
    } catch (err) {
        return err
    }
  };

  export const axiosDELETE = async (URL) => {
    const baseURL= process.env.REACT_APP_BASEURL;
   const concatURL = `${baseURL}${URL}`
    try {
      const res = await axios.delete(concatURL);
      ToastSuccess('Data Deleted Sucessfully');
        return res
    } catch (err) {
        return err
    }
  };

  export const axiosSecurityPOST = async (URL, data) => {
    console.log(data, "dataContent")
    const baseURL= process.env.REACT_APP_SECURITY;
   const concatURL = `${baseURL}${URL}`
    try {
      const res = await axios.post(concatURL, data);
        return res

    } catch (err) {
        return err
    }
  }