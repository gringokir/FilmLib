import { useEffect, useState } from 'react';
import axios from "axios";
import { hasJWT } from "./AuthUtil";
import { useNavigate } from "react-router-dom";
import Table from 'react-bootstrap/Table';
import Card from 'react-bootstrap/Card';
import '../styles/Films.css'
import { Link } from "react-router-dom";

export default function Films() {

  const [films, setFilms] = useState([]);
  const navigate = useNavigate();

  const apiFilmsUrl = process.env.REACT_APP_API_LINK + "/api/films";
  const loginUrl = "/login";

  useEffect(() => {
    if (!hasJWT()){
      navigate(loginUrl);
    }
    axios.get(apiFilmsUrl)
    .then(res => setFilms(res.data))
    .catch((err) => console.error(err));
  }, [])
    
  const rows = [];

  films.forEach(film => {
    rows.push(
      <FilmRow 
      film={film}
      key={film.id} />
    )
  })

  return (
    <>
      <Card className="Card">
      <Card.Header>Film list</Card.Header>
      <Card.Body>
        <Table striped bordered hover >
            <thead>
              <tr>
                <th>Title</th>
                <th>Year</th>
                <th>Genres</th>
              </tr>
            </thead>
            <tbody>{rows}</tbody>
        </Table>
      </Card.Body>
      </Card>
    </>   
  );
}

function FilmRow({film}) {
  return(
    <tr>
      <td><Link to={`/films/film/${film.id}`}>{film.title}</Link> </td>
      <td>{film.yearOfCreation}</td>
      <td>{film.genre.map(genre => {return genre}).join(', ')}</td>
    </tr>
  )
}