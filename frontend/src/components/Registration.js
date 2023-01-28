import { useState } from "react";
import {Form, Button, Card, Alert} from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import '../styles/Login.css'
import axios from "axios";
import { useNavigate } from "react-router-dom";

export default  function Registration(){
    const navigate = useNavigate();

    const [alert, setAlert] = useState(false);
    const [alertMessage, setAlertMessage] = useState("");

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("confirmPassword");

    const apiRegistrationUrl =  process.env.REACT_APP_API_LINK + "/api/registration"

    const handleSubmit = async (event) => {
        event.preventDefault();
  
        const params = new URLSearchParams();
        params.append('username', username);
        params.append('password', password);

        if(password !== confirmPassword){
            setAlertMessage("Passwords are not equal!");
            setAlert(true);
            return;
        }
  
        await axios.post(apiRegistrationUrl, params)
        .then(navigate("/login"))
        .catch((err) => {
          console.error(err);
          setAlertMessage(err.response.data);
          setAlert(true);
        });
    }

    return(
        <>
            <div>
                <Card className="card">
                    <Card.Header>Registration</Card.Header>
                    <Card.Body>
                    <Form className="loginForm" onSubmit={handleSubmit}>
                        <Form.Group className="mb-3" controlId="formBasicEmail">
                            <Form.Label>Username</Form.Label>
                            <Form.Control required type="text" value={username}
                                onChange={e => setUsername(e.target.value)} placeholder="Username" />
                        </Form.Group>

                        <Form.Group className="mb-3" controlId="formPassword">
                            <Form.Label>Password</Form.Label>
                            <Form.Control required type="password" value={password}
                                onChange={e => setPassword(e.target.value)} placeholder="Password" />
                        </Form.Group>

                        <Form.Group className="mb-3" controlId="formConfirmPassword">
                            <Form.Label>Repeat password</Form.Label>
                            <Form.Control required type="password" 
                                onChange={e => setConfirmPassword(e.target.value)} placeholder="Confirm password" />
                        </Form.Group>

                        <Button variant="primary" type="submit">Create account</Button>
                    </Form>
                    {alert && (
                    <Alert className="alert" variant="danger" onClose={() => setAlert(false)} dismissible>
                        <p>{alertMessage}</p>
                    </Alert>
                    )}
                    </Card.Body>
                </Card>
            </div>
        </>
    )
}