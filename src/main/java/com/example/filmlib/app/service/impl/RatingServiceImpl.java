package com.example.filmlib.app.service.impl;

import com.example.filmlib.app.entity.Film;
import com.example.filmlib.app.entity.FilmRating;
import com.example.filmlib.app.entity.User;
import com.example.filmlib.app.repository.RatingRepo;
import com.example.filmlib.app.repository.UserRepo;
import com.example.filmlib.app.service.RatingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RatingServiceImpl implements RatingService {
    private final RatingRepo ratingRepo;
    private final UserRepo userRepo;

    public RatingServiceImpl(RatingRepo ratingRepo, UserRepo userRepo) {
        this.ratingRepo = ratingRepo;
        this.userRepo = userRepo;
    }

    @Override
    public FilmRating changeRating(User user, Film film, int rating) {
        FilmRating filmRating = new FilmRating(user, film, rating);
        ratingRepo.save(filmRating);
        addFilmToWatchedList(user, film);
        return filmRating;
    }

    private void addFilmToWatchedList(User user, Film film) {
        user.getWatchedFilms().add(film);
        userRepo.save(user);
    }

    @Override
    @Transactional
    public FilmRating findRatingByFilmAndUser(Film film, User user) {
        return ratingRepo.findRatingByFilmAndUser(film, user);
    }

    @Override
    @Transactional
    public void deleteFilmRatingByFilmAndUser(Film film, User user) {
        ratingRepo.deleteFilmRatingByFilmAndUser(film, user);
        deleteFilmFromUser(film, user);
        refreshRating(film);
    }

    private void deleteFilmFromUser(Film film, User user) {
        user.getWatchedFilms().remove(film);
        userRepo.save(user);
    }

    @Override
    public void refreshRating(Film film) {
        if (film.getFilmRatings().isEmpty()) {
            film.setRating(0.0);
            return;
        }
        film.setRating(film.getFilmRatings()
                .stream()
                .mapToDouble(FilmRating::getRating)
                .average()
                .orElseThrow(IllegalStateException::new)
        );
    }
}
