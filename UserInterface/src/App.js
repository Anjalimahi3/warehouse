import logo from './logo.svg';
import './App.css';
import { Fragment } from 'react';
import RoutesPath from './Routes';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';

function App() {
  return (
    <Fragment>
      <BrowserRouter>
        <RoutesPath/>
        <ToastContainer />
      </BrowserRouter>
    </Fragment>
  );
}

export default App;
