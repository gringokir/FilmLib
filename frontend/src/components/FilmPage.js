import axios from "axios";
import { useEffect, useState } from "react";
import { useParams } from 'react-router-dom';
import '../styles/Images.css';
import '../styles/Container.css'
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

export default function FilmPage() {
    const { id } = useParams();
    const [film, setFilm] = useState("");

    const apiFilmUrl = process.env.REACT_APP_API_LINK + `/api/films/film/${id}`;

    useEffect(() => {
        axios.get(apiFilmUrl)
        .then(res => setFilm(res.data))
        .catch((err) => console.error(err));
    }, []);

    if(film!==undefined){
        return(
            <>
            <Container className="container">
                <Row>
                    <Col sm={4}>
                        <img src={film.posterUrl} className="filmPage"/>
                    </Col>
                    <Col>
                    <h1>{film.title} ( {film.yearOfCreation} )</h1>
                    <h3>Genres:</h3>
                    <ul>
                        {film.genre?.map(item => {
                            return <li key={item}>{item}</li>;
                        })}
                    </ul>
                    </Col>
                </Row>
            </Container>
            </>
        );
    } else {
        return(<span>Loading...</span>)
    }
}