import { useRef, useState } from "react";

export default function Login() {
    function handleClick(username) {
        fetch('http://localhost:8081/login', {
        method: 'POST',
        headers:{
          'Content-Type': 'application/x-www-form-urlencoded'
        },    
        body: new URLSearchParams({
            'username': username,
            'password': '1'
        })
        });
      }
    
      const [username, setUsername] = useState('');
      // const [password, setPassword] = useState('');
      const inputRef = useRef();
    
      const submitHandler = (e) => {
        e.preventDefault();
    
        setUsername(inputRef.current.value);
        handleClick(username);
      }
    
      return (
        <div className="App">    
          <form onSubmit={submitHandler} className="App-header">
            <input type='text' ref={inputRef} placeholder="Username" />
            {/* <input type='password' ref={inputRef} placeholder="Password" /> */}
            <input type="submit" />
          </form>
        </div>
      )
}