package com.filmlib.service;

import com.filmlib.entity.Film;
import com.filmlib.entity.FilmRating;
import com.filmlib.entity.User;

public interface RatingService {
    FilmRating changeRating(User user, Film film, int rating);

    void refreshRating(Film film);

    FilmRating findRatingByFilmAndUser(Film film, User user);

    void deleteFilmRatingByFilmAndUser(Film film, User user);

}
