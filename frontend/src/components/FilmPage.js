import axios from "axios";
import { useEffect, useState } from "react";
import { useParams } from 'react-router-dom';

export default function FilmPage() {
    const { id } = useParams();
    const [film, setFilm] = useState("");
    const url = `http://localhost:8081/films/film/${id}`;

    useEffect(() => {
        axios.get(url)
        .then(res => setFilm(res.data))
        .catch((err) => console.error(err));
    }, []);

    if(film!==undefined){
        return(
            <div>
                <h1>{film.title} ( {film.yearOfCreation} )</h1>
            </div>
        );
    } else {
        return(<span>Loading...</span>)
    }

   
}