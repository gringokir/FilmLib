package com.example.filmlib.app.service;

import com.example.filmlib.app.entity.Film;
import com.example.filmlib.app.entity.FilmRating;
import com.example.filmlib.app.entity.User;

public interface RatingService {
    void changeRating(User user, Film film, int rating);

    void refreshRating(Film film);

    FilmRating findRatingByFilmAndUser(Film film, User user);

    void deleteFilmRatingByFilmAndUser(Film film, User user);

}
