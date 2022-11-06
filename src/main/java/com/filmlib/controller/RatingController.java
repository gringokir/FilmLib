package com.filmlib.controller;

import com.filmlib.entity.Film;
import com.filmlib.entity.FilmRating;
import com.filmlib.entity.User;
import com.filmlib.service.RatingService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/films")
public class RatingController {

    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping("/getRating/{film}/{user}")
    public FilmRating getRating(@PathVariable Film film, @PathVariable User user) {
        return ratingService.findRatingByFilmAndUser(film, user);
    }

    @PostMapping("/changeRating/{film}/{user}")
    public String changeRating(@PathVariable Film film, @PathVariable User user, int rating) {
        ratingService.changeRating(user, film, rating);
        return "redirect:/films";
    }

    @GetMapping("/deleteRating/{film}/{user}")
    public String deleteRating(@PathVariable Film film, @PathVariable User user) {
        ratingService.deleteFilmRatingByFilmAndUser(film, user);
        return "redirect:/films";
    }
}
