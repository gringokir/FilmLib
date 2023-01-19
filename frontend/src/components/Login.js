import { useState, useContext } from "react";
import {Form, Button, Card, Alert} from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import '../styles/Login.css'
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { AuthContext } from './AuthUtil';

export default function Login() {
  const navigate = useNavigate();
  const {isAuth, setAuth} = useContext(AuthContext);
  const [alert, setAlert] = useState(false);
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const apiLoginUrl = process.env.REACT_APP_API_LINK + "/api/login";

  const handleSubmit = (event) => {
      event.preventDefault();

      const params = new URLSearchParams();
      params.append('username', username);
      params.append('password', password);

      axios.post(apiLoginUrl, params)
      .then(json => {
        localStorage.setItem("accessToken", json.data.accessToken);
        setAuth(true);
        navigate("/");
      })
      .catch((err) => {
        console.error(err);
        setAlert(true);
      });
  }

  return (
    <>
    <div>
      <Card className="card">
        <Card.Header>Login</Card.Header>
        <Card.Body>
          <Form className="loginForm" onSubmit={handleSubmit}>
            <Form.Group className="mb-3" controlId="formBasicEmail">
              <Form.Label>Username</Form.Label>
              <Form.Control required type="text" value={username}
               onChange={e => setUsername(e.target.value)} placeholder="Username" />
            </Form.Group>

            <Form.Group className="mb-3" controlId="formBasicPassword">
              <Form.Label>Password</Form.Label>
              <Form.Control required type="password" value={password}
               onChange={e => setPassword(e.target.value)} placeholder="Password" />
            </Form.Group>

            <Button variant="primary" type="submit">Login</Button>
          </Form>
          {alert && (
          <Alert className="alert" variant="danger" onClose={() => setAlert(false)} dismissible>
              <p>Username or passowrd are incorrect!</p>
          </Alert>
          )}
        </Card.Body>
      </Card>
    </div>
    </>
  )
}