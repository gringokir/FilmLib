import '../styles/App.css';
import { Routes, Route, BrowserRouter } from "react-router-dom";
import Welcome from './Welcome';
import Login from './Login';
import Navbar from './NavBar';
import Films from './Films'
import 'bootstrap/dist/css/bootstrap.min.css';
import { useState } from 'react';
import {AuthContext} from './auth';
import { hasJWT } from "./auth";

export default function App() {  
  const [isAuth, setAuth] = useState(hasJWT);
  return(
    <div>
      <AuthContext.Provider value={{isAuth, setAuth}}>
      <BrowserRouter>
      <Navbar />
        <Routes>
          <Route path='/login' element={<Login />} />
          <Route reloadDocument path='/films' element={<Films />} />
          <Route path='/' element={<Welcome />} />
        </Routes>
    </BrowserRouter>
    </AuthContext.Provider>
    </div>
  )
}