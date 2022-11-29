import './App.css';
import { useRef, useState } from "react";

// function App() {
//   return (
//     <div className="App">
//       <header className="App-header">
//         {/* <img src={logo} className="App-logo" alt="logo" /> */}
//         <p>
//           Welcome to FilmLib
//         </p>
//       </header>
//     </div>
//   );
// }

export default function App() {  
  
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