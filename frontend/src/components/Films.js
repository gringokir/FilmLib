import { useEffect, useState } from 'react';
import axios from "axios";
import { hasJWT } from "./AuthUtil";
import { useNavigate } from "react-router-dom";
import Table from 'react-bootstrap/Table';
import Card from 'react-bootstrap/Card';
import '../styles/Films.css'
import { Link } from "react-router-dom";
import { getUserIdFromToken } from './AuthUtil';
import Dropdown from 'react-bootstrap/Dropdown';
import { DropdownButton } from 'react-bootstrap';

export default function Films() {

  const [films, setFilms] = useState([]);
  const navigate = useNavigate();

  const apiFilmsUrl = process.env.REACT_APP_API_LINK + "/api/films";
  const apiUserRatingsUrl = process.env.REACT_APP_API_LINK + "/api/films/getRating/";
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

  function FilmRow({film}) {
    return(
      <tr>
        <td><Link to={`/films/film/${film.id}`}>{film.title}</Link> </td>
        <td>{film.yearOfCreation}</td>
        <td>{film.genre.map(genre => {return genre}).join(', ')}</td>
        <td>{Math.round(film.rating * 100) / 100}</td>
        <td><Rating film={film} /></td>
      </tr>
    )
  }

  function UserRating({film}){
    let ratingUrl = apiUserRatingsUrl + film.id + "/" + getUserIdFromToken();
    const [rating, setRating] = useState([]);

    axios.get(ratingUrl)
    .then(res => setRating(res.data.rating));
    return rating;
  }

  function Rating({film}) {
    return(
      <>
      <UserRating className="UserRating" film={film} />
      <DropdownButton className="Dropdown" title="Rate" size="sm" onSelect={(eventKey)=>handleSelect(film.id, eventKey)}>
        <Dropdown.Item eventKey={1}>1</Dropdown.Item>
        <Dropdown.Item eventKey={2}>2</Dropdown.Item>
        <Dropdown.Item eventKey={3}>3</Dropdown.Item>
        <Dropdown.Item eventKey={4}>4</Dropdown.Item>
        <Dropdown.Item eventKey={5}>5</Dropdown.Item>
      </DropdownButton>
      </>
    )

    function handleSelect (filmId, eventKey) {
      axios.post(apiFilmsUrl + "/changeRating/" + filmId + "/" + getUserIdFromToken() + "/" + eventKey)
      .then(window.location.reload())
    }
  }

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
                <th>Rating</th>
                <th>Your rating</th>
              </tr>
            </thead>
            <tbody>{rows}</tbody>
        </Table>
      </Card.Body>
      </Card>
    </>   
  );
}
