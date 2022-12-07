import { Button } from 'react-bootstrap';
import { useNavigate } from "react-router-dom";

export default function Welcome() {
    let navigate = useNavigate(); 
    return (
        <div className="App">
            <header className="App-header">
            <p>
                Welcome to FilmLib
            </p>
            <Button variant="light" onClick={() => navigate("/films")}>Go to film library</Button>
            </header>
        </div>
    );
}