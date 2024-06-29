import React from "react";
import Avatar from "@mui/material/Avatar";
import Button from "@mui/material/Button";
import CssBaseline from "@mui/material/CssBaseline";
import TextField from "@mui/material/TextField";
import { BrowserRouter as Router, Link } from "react-router-dom";
import VpnKey from '@mui/icons-material/VpnKey';
import Typography from "@mui/material/Typography";
import Container from "@mui/material/Container";
import ArrowBack from '@mui/icons-material/ArrowBack';
import Style from './Authentication.module.scss';


export default function Forgotpassword() {
  

  return (
    <Container component="main" maxWidth="xs" className="mt-5" style={{height:'100vh'}}>
      <CssBaseline />
      <div className={`${Style.paper} w-100 m-auto`}>
        <div className="w-100 text-center d-flex justify-content-center">
        <Avatar className={Style.avatar}>
          <ArrowBack />
        </Avatar>
        </div>
        <Typography component="h1" variant="h5" className="mt-2">
          Forgot Password
        </Typography>
        <form className="form" noValidate>
          <TextField
            variant="outlined"
            margin="normal"
            required
            fullWidth
            id="email"
            label="Email Address"
            name="email"
            autoComplete="email"
            autoFocus
          />
          <Button
            type="text"
            fullWidth
            variant="text"
            color="secondary"
            
          >
            Send OTP
          </Button>
          <TextField
            variant="outlined"
            margin="normal"
            required
            fullWidth
            id="email"
            label="Verification Code"
            name="email"
            autoComplete="email"
            autoFocus
          />
           <Link to="/signin" variant="body2" className="backButton">
                <ArrowBack fontSize="small" className="me-1"/>Back
              </Link>
          <Button
            type="submit"
            fullWidth
            variant="contained"
            color="primary"
            className={`${Style.buttonSignIn} mt-2`}
          >
            Verify
          </Button>
        
        </form>
      </div>
    </Container>
  );
}
