import React, { useEffect, useState } from 'react';

function App() {
  const [films, setFilms] = useState([])

  useEffect(()=>{
    fetch("http://localhost:8081/films")
    .then(res=>res.json())
    .then((result)=>{setFilms(result);})
    .catch((err) => console.error(err))
  }, [])

  return (
    <div className="App">
      <header className="App-header">
        <p>
          Welcome to FilmLib
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

export default App;