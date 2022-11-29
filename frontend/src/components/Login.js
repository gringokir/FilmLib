import { useRef, useState } from "react";
import {Form, Button, Card} from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import './Login.css'

export default function Login() {
    function handleSubmit() {
        fetch('http://localhost:8081/login', {
        method: 'POST',
        headers:{
          'Content-Type': 'application/x-www-form-urlencoded'
        },    
        body: new URLSearchParams({
            'username': username,
            'password': password
        })
        });
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
                <Button variant="primary" type="submit">
                  Submit
                </Button>
              </Form>
            </Card.Body>
          </Card>
        </div>
        </>
      )
}