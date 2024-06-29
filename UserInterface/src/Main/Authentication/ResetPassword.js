import React from "react";
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import Typography from "@mui/material/Typography";
import Container from "@mui/material/Container";
import Avatar from "@mui/material/Avatar";
import ArrowBack from '@mui/icons-material/ArrowBack';
import LockOpenIcon from '@mui/icons-material/LockOpen';
import { Link } from "react-router-dom";
import Style from './Authentication.module.scss';

export default function ResetPassword() {
  

  return (
    <Container component="main" maxWidth="xs" style={{height:'100vh'}} className="mt-5">
     <div className={`${Style.paper} w-100 m-auto`}>
        <div className="w-100 text-center d-flex justify-content-center">
        <Avatar className={Style.avatar}>
          <LockOpenIcon />
        </Avatar>
        </div>
        <Typography component="h1" variant="h5" className="mt-2">
          Reset Your Password
        </Typography>
        <form className="form" noValidate>
          <TextField
            variant="outlined"
            margin="normal"
            required
            fullWidth
            id="New Password"
            label="New Password"
            name="newPassword"
            autoComplete="email"
            autoFocus
          />
          <TextField
            variant="outlined"
            margin="normal"
            required
            fullWidth
            id="Reenter Password"
            label="Reenter Password"
            name="reenterpassword"
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
            className={`${Style.buttonSignIn} mt-3`}
          >
            Verify
          </Button>
        
        </form>
      </div>
    </Container>
  );
}
