import React, { useState } from "react";
import Avatar from "@mui/material/Avatar";
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import FormControlLabel from "@mui/material/FormControlLabel";
import Checkbox from "@mui/material/Checkbox";
import { Link } from "react-router-dom";
import Grid from "@mui/material/Grid";
import LockIcon from '@mui/icons-material/Lock';
import Typography from "@mui/material/Typography";
import Container from "@mui/material/Container";
import Style from './Authentication.module.scss';
import { axiosSecurityPOST } from "../Utils/Axios";
import { useNavigate } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { PROFILEDETAIL } from '../../Store/action';
export default function SignIn() {
  const navigate = useNavigate();
  const [credi, setCredi] = useState({
    userName:"",
    password:""
  });
  const dispatch = useDispatch();
  const signIn=async(e)=>{
    e.preventDefault();
      const data={
          "userEmail": credi.userName,
          "password": credi.password
      
      }
      const response = await axiosSecurityPOST(process.env.REACT_APP_AUTHENTICATION, data);
      // sessionStorage.setItem(id, value);
      if(response.status === 200){
        const token = response.data.jwttoken;
        sessionStorage.setItem("tokenValue", token);
        sessionStorage.setItem("userEmail", credi.userName);
        sessionStorage.setItem("userName", response.data.userName);
        sessionStorage.setItem("role", response.data.role);
        console.log(response)
        dispatch({type:PROFILEDETAIL, Payload:response.data})
        navigate("/dashboard");
      }
  }

  const onHandleChange=(e)=>{
    e.preventDefault();
    setCredi((previous)=>{
      return {...previous,
      [e.target.name]:e.target.value}
    })
  }


  return (
    <Container component="main" maxWidth="xs" className="mt-5">
      
      <div className={`${Style.paper} w-100 m-auto`}>
        <div className="w-100 text-center d-flex justify-content-center">
        <Avatar className={Style.avatar}>
          <LockIcon />
        </Avatar>
        </div>
        <Typography component="h1" variant="h5" className="w-100 text-center">
          Sign in
        </Typography>
        <form className="form" onSubmit={signIn} noValidate>
          <TextField
            variant="outlined"
            margin="normal"
            required
            fullWidth
            id="email"
            label="Email Address"
            name="userName"
            autoComplete="email"
            autoFocus
            onChange={onHandleChange}
          />
          
          <TextField
            variant="outlined"
            margin="normal"
            required
            fullWidth
            name="password"
            label="Password"
            type="password"
            id="password"
            autoComplete="current-password"
            onChange={onHandleChange}
          />
          <FormControlLabel
            control={<Checkbox value="remember" color="primary" />}
            label="Remember me"
          />
          <Button
            type="submit"
            fullWidth
            variant="contained"
            className={Style.buttonSignIn}
          >
            Sign In
          </Button>
          <Grid container className="mt-2 backButton">
            <Grid item xs>
              <Link to="/forgotpassword" variant="body2">
                Forgot password?
              </Link>
            </Grid>
            <Grid item>
              <Link to="/signup" variant="body2">
                {"Don't have an account? Sign Up"}
              </Link>
            </Grid>
          </Grid>
        </form>
      </div>
    </Container>
  );
}
