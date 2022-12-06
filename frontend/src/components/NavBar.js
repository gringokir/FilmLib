import { useEffect, useState, useContext } from 'react';
import {Navbar, Nav} from 'react-bootstrap';
import { hasJWT, AuthContext } from "./auth";
import { useNavigate, redirect, Link } from "react-router-dom";

export default function NavBar() {
    const [isLoggedIn, setIsLoggedIn] = new useState(); 
    useEffect(() => setIsLoggedIn(hasJWT), []);
    
    const navigate = useNavigate();

    function logout(){
        localStorage.removeItem("accessToken");
        navigate("/login");
    }

    const {isAuth, setAuth} = useContext(AuthContext);

    return(
        <Navbar bg="light" variant="light">
        <Navbar.Brand to="/">FilmLib</Navbar.Brand>
            <Nav className="me-auto">
                <Nav.Link as={Link} to="/">Home</Nav.Link>
                <Nav.Link as={Link} to="/login">Login</Nav.Link>
                {isAuth && (
                    <Nav.Link reloadDocument as={Link} to="/films">Films</Nav.Link>
                )
                }
                <Navbar.Toggle />
            </Nav>
            <Nav>
                <Navbar.Collapse className="justify-content-end">
                    {isAuth && (
                        <div>
                            <Navbar.Text>Logged In</Navbar.Text>
                            <Nav.Link reloadDocument as={Link} to="/login" onClick={logout}>Log out</Nav.Link>
                        </div>
                        )
                    }
                    {!isAuth && (
                        <Nav.Link as={Link} to="/login">Login</Nav.Link>
                        )
                    }
                </Navbar.Collapse>
            </Nav>
    </Navbar>
    )
}