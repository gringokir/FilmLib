import './App.css';
import { Routes, Route } from "react-router-dom";
import Welcome from './Welcome';
import Login from './Login';
import Navbar from './NavBar';
import Films from './Films';
import { BrowserRouter } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';

export default function App() {  
  return(
    <div>
    <BrowserRouter>
      <Navbar />
        <Routes>
          <Route path='/login' element={<Login />} />
          <Route path='/films' element={<Films />} />
          <Route path='/' element={<Welcome />} />
        </Routes>
    </BrowserRouter>
    </div>
  )
}