import React from "react";
import Avatar from "@mui/material/Avatar";
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import { Link } from "react-router-dom";
import PersonIcon from '@mui/icons-material/Person';
import ArrowBack from '@mui/icons-material/ArrowBack';
import Typography from "@mui/material/Typography";
import Container from "@mui/material/Container";
import Style from './Authentication.module.scss';
import Box from '@mui/material/Box';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select, { SelectChangeEvent } from '@mui/material/Select';

export default function SignUp() {
  const [age, setAge] = React.useState('');

  const handleChange = (event) => {
    setAge(event.target.value);
  };
  return (
    <Container component="main" maxWidth="xs" className="mt-5" style={{height:'100vh'}}>
   <div className="paper w-100 m-auto">
        <div className="w-100 text-center d-flex justify-content-center">
        <Avatar className="avatar ">
          <PersonIcon />
        </Avatar>
        </div>
        <Typography component="h1" variant="h5" className="w-100 text-center">
          Sign Up
        </Typography>
        <form className="form" noValidate>
        <TextField
            variant="outlined"
            margin="normal"
            required
            fullWidth
            id="name"
            label="Name"
            name="name"
            autoComplete="name"
            autoFocus
          />
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
          <Box sx={{ minWidth: 120 }}>
      <FormControl fullWidth>
        <InputLabel id="demo-simple-select-label">Role</InputLabel>
        <Select
          labelId="demo-simple-select-label"
          id="demo-simple-select"
          value={age}
          label="Age"
          onChange={handleChange}
        >
          <MenuItem value={10}>Ten</MenuItem>
          <MenuItem value={20}>Twenty</MenuItem>
          <MenuItem value={30}>Thirty</MenuItem>
        </Select>
      </FormControl>
    </Box>
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
          />
            <TextField
            variant="outlined"
            margin="normal"
            required
            fullWidth
            name="password"
            label="Confirm Password"
            type="password"
            id="password"
            autoComplete="current-password"
          />
          <Link to="/signin"  className={Style.backButton}>
                <ArrowBack fontSize="small" className="me-1"/>Back
              </Link>
          <Button
            type="submit"
            fullWidth
            variant="contained"
            color="primary"
            className={Style.buttonSignup}
          >
            Sign Up
          </Button>
        </form>
      </div>
    </Container>
  );
}
