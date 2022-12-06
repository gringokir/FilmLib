import React, { useEffect, useState } from 'react';
import axios from "axios";
import { hasJWT } from "./auth";
import { useNavigate } from "react-router-dom";

export default function Films() {
  const [films, setFilms] = useState([])
  const navigate = useNavigate();

  useEffect(() => {
    if (!hasJWT()){
      navigate("/login");
    }
    let result = axios.get("http://localhost:8081/films")
    .then(res => setFilms(res.data))
    .catch((err) => console.error(err));
  }, [])

  return (
    <div className="App">
      <header className="App-header">
        <p>
          Film List
        </p>
        {films.map((film)=>{
          return(
            <div key={film.id}>
              <p>Title: {film.title}</p>
            </div>
          )
        })}

      </header>
    </div>
  );
}