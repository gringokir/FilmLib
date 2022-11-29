import {Navbar, Nav, Container} from 'react-bootstrap'
import { Link } from 'react-router-dom'

export default function NavBar() {
    return(
        <Navbar bg="light" variant="light">
            <Navbar.Brand to="/">FilmLib</Navbar.Brand>
                <Nav className="me-auto">
                    <Nav.Link as={Link} to="/">Home</Nav.Link>
                    <Nav.Link as={Link} to="/login">Login</Nav.Link>
                    <Nav.Link as={Link} to="/films">Films</Nav.Link>
                </Nav>
        </Navbar>
    )
}