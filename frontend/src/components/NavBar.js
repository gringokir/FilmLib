import { useEffect, useState, useContext } from 'react';
import {Navbar, Nav} from 'react-bootstrap';
import { hasJWT, AuthContext, getUsernameFromToken } from "./AuthUtil";
import { useNavigate, Link } from "react-router-dom";
import '../styles/Navbar.css'

export default function NavBar() {
    const [isLoggedIn, setIsLoggedIn] = new useState();
    const [username, setUsername] = new useState();
    const {isAuth, setAuth} = useContext(AuthContext);

    const loginUrl = "/login";
    const filmsUrl = "/films";
    const userUrl = `/user/${username}`;

    useEffect(() => {
        setIsLoggedIn(hasJWT);
        let usernameFromToken = getUsernameFromToken();
        if(usernameFromToken){
            setUsername(usernameFromToken);
        }
    }, []);
    
    const navigate = useNavigate();

    function logout(){
        localStorage.removeItem("accessToken");
        navigate("/");
    }

    return(
        <Navbar className="navbar" bg="light" variant="light">
        <Navbar.Brand to="/">FilmLib</Navbar.Brand>
            <Nav className="me-auto">
                <Nav.Link as={Link} to="/">Home</Nav.Link>
                {isAuth && (
                    <Nav.Link reloadDocument as={Link} to={filmsUrl}>Films</Nav.Link>
                )
                }
                <Navbar.Toggle />
            </Nav>
            <Nav>
                {isAuth ? (
                    <>
                        <Nav.Link reloadDocument as={Link} onClick={() => navigate(userUrl)}>{username}</Nav.Link>
                        <Nav.Link reloadDocument as={Link} onClick={logout}>Log out</Nav.Link>
                    </>
                    ) : (
                        <Nav.Link as={Link} to={loginUrl}>Login</Nav.Link>
                    )
                }
            </Nav>
    </Navbar>
    )
}