package com.filmlib.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.filmlib.entity.Film;
import com.filmlib.entity.FilmRating;
import com.filmlib.entity.User;
import com.filmlib.entity.Views;
import com.filmlib.service.RatingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/films")
@Slf4j
public class RatingController {

    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping("/getRating/{film}/{user}")
    @JsonView(Views.User.class)
    public FilmRating getRating(@PathVariable Film film, @PathVariable User user) {
        return ratingService.findRatingByFilmAndUser(film, user);
    }

    @PostMapping("/changeRating/{film}/{user}/{rating}")
    public ResponseEntity<String> changeRating(
            @PathVariable Film film,
            @PathVariable User user,
            @PathVariable Integer rating
    ) {
        if (rating < 1 || rating > 5) {
            log.info("Inappropriate rating: " + rating + " is submitted.");
            return ResponseEntity.status(422).body("Inappropriate rating: " + rating + ". It should be from 1 to 5");
        }
        ratingService.changeRating(user, film, rating);
        log.info("Rating " + rating + " from " + user.getUsername() + " for film " + film.getTitle() + " was added.");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/deleteRating/{film}/{user}")
    public ResponseEntity<String> deleteRating(@PathVariable Film film, @PathVariable User user) {
        ratingService.deleteFilmRatingByFilmAndUser(film, user);
        log.info("Rating from " + user.getUsername() + " for film " + film.getTitle() + " was removed.");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
