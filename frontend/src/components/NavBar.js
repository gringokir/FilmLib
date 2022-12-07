import { useEffect, useState, useContext } from 'react';
import {Navbar, Nav} from 'react-bootstrap';
import { hasJWT, AuthContext } from "./auth";
import { useNavigate, Link } from "react-router-dom";
import '../styles/Navbar.css'

export default function NavBar() {
    const [isLoggedIn, setIsLoggedIn] = new useState();
    const [username, setUsername] = new useState();
    const {isAuth, setAuth} = useContext(AuthContext);
    useEffect(() => {
        setIsLoggedIn(hasJWT);
        getUsernameFromToken();
    }, []);
    
    const navigate = useNavigate();

    function logout(){
        localStorage.removeItem("accessToken");
        navigate("/login");
    }

    function getUsernameFromToken() {
        let token = localStorage.getItem("accessToken");
        if(!token) { return; }
        const base64url = token.split('.')[1];
        const base64 = base64url.replace('-', '+').replace('_', '/');
        setUsername(JSON.parse(window.atob(base64)).sub);
    }


    return(
        <Navbar className="navbar" bg="light" variant="light">
        <Navbar.Brand to="/">FilmLib</Navbar.Brand>
            <Nav className="me-auto">
                <Nav.Link as={Link} to="/">Home</Nav.Link>
                {isAuth && (
                    <Nav.Link reloadDocument as={Link} to="/films">Films</Nav.Link>
                )
                }
                <Navbar.Toggle />
            </Nav>
            <Nav>
                {isAuth && (
                    <>
                        <Nav.Link reloadDocument as={Link} onClick={() => navigate(`/user/${username}`)}>{username}</Nav.Link>
                        <Nav.Link reloadDocument as={Link} onClick={logout}>Log out</Nav.Link></>
                    )
                }
                {!isAuth && (
                    <Nav.Link as={Link} to="/login">Login</Nav.Link>
                    )
                }
            </Nav>
    </Navbar>
    )
}