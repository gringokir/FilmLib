package com.example.filmlib.app.repository;

import com.example.filmlib.app.entity.Film;
import com.example.filmlib.app.entity.FilmRating;
import com.example.filmlib.app.entity.FilmRatingKey;
import com.example.filmlib.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepo extends JpaRepository<FilmRating, FilmRatingKey> {
    FilmRating findRatingByFilmAndUser(Film film, User user);
    void deleteFilmRatingByFilmAndUser(Film film, User user);
}
