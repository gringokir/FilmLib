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

export function getUsernameAndIdFromToken() {
      let token = localStorage.getItem("accessToken");
      if(!token) { return; }
      const base64url = token.split('.')[1];
      const base64 = base64url.replace('-', '+').replace('_', '/');
      return JSON.parse(window.atob(base64)).sub;
  }