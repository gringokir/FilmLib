import axios from 'axios';
import { createContext } from 'react';

export const AuthContext = createContext(hasJWT);

export const setAuthToken = token => {
    if (token) {
        axios.defaults.headers.common["Authorization"] = `Bearer ${token}`;
    }
    else
        delete axios.defaults.headers.common["Authorization"];
}

export function hasJWT() {
    let flag = false;
  
    localStorage.getItem("accessToken") ? flag=true : flag=false
    
    return flag;
  }