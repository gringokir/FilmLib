import { useNavigate } from "react-router-dom";
import { Nav, Button } from 'react-bootstrap';
import { Link } from "react-router-dom";

export default function Welcome() {
    let navigate = useNavigate(); 
    return (
        <div className="App">
            <header className="App-header">
            <h1>
                Welcome to FilmLib
            </h1>
            <br />
            <Nav.Link reloadDocument as={Link} to="/films">
                <Button variant="light" size="lg">Go to the film library</Button>
            </Nav.Link>
            </header>
        </div>
    );
}