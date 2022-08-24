package com.example.filmlib.app.repository;

import com.example.filmlib.app.entity.Film;
import com.example.filmlib.app.entity.FilmRating;
import com.example.filmlib.app.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RatingRepoTest {

    @Autowired
    private RatingRepo underTest;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private FilmRepo filmRepo;
    private User user;
    private Film film;

    @BeforeEach
    void setUp() {
        user = new User("john", "123", true);
        userRepo.save(user);
        film = new Film("Titanic", 1983);
        filmRepo.save(film);
    }

    @Test
    void shouldFindRatingByFilmAndUser() {
        //given
        FilmRating filmRating = new FilmRating(user, film, 5);
        underTest.save(filmRating);

        //when
        FilmRating foundedFilmRating = underTest.findRatingByFilmAndUser(film,user);

        //then
        assertThat(foundedFilmRating).isEqualTo(filmRating);
    }

    @Test
    void shouldNotFindRatingByFilmAndUser() {
        //when
        FilmRating foundedFilmRating = underTest.findRatingByFilmAndUser(film,user);

        //then
        assertThat(foundedFilmRating).isNull();
    }

    @Test
    void shouldDeleteFilmRatingByFilmAndUser() {
        //given
        FilmRating filmRating = new FilmRating(user, film, 5);
        underTest.save(filmRating);

        //when
        underTest.deleteFilmRatingByFilmAndUser(film, user);
        FilmRating foundedFilmRating = underTest.findRatingByFilmAndUser(film,user);

        //then
        assertThat(foundedFilmRating).isNull();
    }
}