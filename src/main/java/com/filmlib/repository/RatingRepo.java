package com.filmlib.repository;

import com.filmlib.entity.Film;
import com.filmlib.entity.FilmRating;
import com.filmlib.entity.FilmRatingKey;
import com.filmlib.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepo extends JpaRepository<FilmRating, FilmRatingKey> {
    FilmRating findRatingByFilmAndUser(Film film, User user);
    void deleteFilmRatingByFilmAndUser(Film film, User user);
}
