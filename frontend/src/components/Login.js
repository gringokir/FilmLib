import { useState, useContext } from "react";
import {Form, Button, Card} from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import '../styles/Login.css'
import axios from "axios";
import { useNavigate } from "react-router-dom";
import {AuthContext} from './auth';

export default function Login() {
  const navigate = useNavigate();
  const {isAuth, setAuth} = useContext(AuthContext);

  const handleSubmit = (event) => {
      event.preventDefault();

      const params = new URLSearchParams();
      params.append('username', username);
      params.append('password', password);

      axios.post('http://localhost:8081/login', params)
      .then(json => {
        localStorage.setItem("accessToken", json.data.accessToken);
      })
      .catch((err) => console.error(err));

      setAuth(true);
      navigate("/");
  }
    
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  return (
    <>
    <div>
      <Card className="Card">
        <Card.Header>Login</Card.Header>
        <Card.Body>
          <Form id="loginForm" onSubmit={handleSubmit} className="LoginForm">
            <Form.Group className="mb-3" controlId="formBasicEmail">
              <Form.Label>Username</Form.Label>
              <Form.Control required type="text" value={username} onChange={e => setUsername(e.target.value)} placeholder="Username" />
            </Form.Group>

            <Form.Group className="mb-3" controlId="formBasicPassword">
              <Form.Label>Password</Form.Label>
              <Form.Control required type="password" value={password} onChange={e => setPassword(e.target.value)} placeholder="Password" />
            </Form.Group>

            <Button variant="primary" type="submit">Submit</Button>
          </Form>
        </Card.Body>
      </Card>
    </div>
    </>
  )
}