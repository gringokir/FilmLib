import { useEffect, useState } from "react";
import { useParams } from 'react-router-dom';
import axios from "axios";
import Table from 'react-bootstrap/Table';
import { Link } from "react-router-dom";

export default function UserPage() {
    const {username} = useParams();
    const[user, setUser] = useState();
    const[filmRatings, setFilmRatings] = useState([]);

    const apiUserUrl = process.env.REACT_APP_API_LINK + `/api/users/user/${username}`;
    const filmUrl = `/films/film/`;

    useEffect(() => {
        axios.get(apiUserUrl)
        .then(res => {
            setUser(res.data);
            setFilmRatings(res.data.filmRatings)
        })
        .catch((err) => console.error(err));
    }, [])

    const rows = [];

    filmRatings.forEach(ratingObj => {
        rows.push(
        <FilmRow 
        film={ratingObj.film}
        rating={ratingObj.rating}
        key={ratingObj.film.id} />
        )
    })

    function FilmRow({film, rating}) {
        return(
          <tr>
            <td><Link to={filmUrl+`${film.id}`}>{film.title}</Link></td>
            <td>{rating}</td>
          </tr>
        )
      }

    if(user){
        return(
        <>
        <h1>Hello, {user.username}</h1>
        <Table style={{margin:"auto", width:"50%"}} striped bordered hover >
            <thead>
              <tr>
                <th>Title</th>
                <th>Your rating</th>
              </tr>
            </thead>
            <tbody>{rows}</tbody>
        </Table>
        </>
    )} else {
        return(
            <h1>Loading...</h1>
        )
    }
}