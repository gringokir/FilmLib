package com.example.filmlib.app.service.impl;

import com.example.filmlib.app.entity.Film;
import com.example.filmlib.app.entity.FilmRating;
import com.example.filmlib.app.entity.User;
import com.example.filmlib.app.repository.FilmRepo;
import com.example.filmlib.app.repository.RatingRepo;
import com.example.filmlib.app.repository.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RatingServiceImplTest {

    @InjectMocks
    private RatingServiceImpl underTest;
    @Mock
    private UserRepo userRepo;
    @Mock
    private RatingRepo ratingRepo;
    @Mock
    private FilmRepo filmRepo;
    private User user;
    private Film film;

    @BeforeEach
    void setUp() {
        user = new User("john", "123", true);
        user.setId(1L);
        userRepo.save(user);
        film = new Film("Titanic", 1983);
        film.setId(2L);
        filmRepo.save(film);
    }

    @Test
    void shouldChangeRating() {
        //given
        ArgumentCaptor<FilmRating> ratingArgumentCaptor = ArgumentCaptor.forClass(FilmRating.class);

        //when
        underTest.changeRating(user, film, 3);
        verify(ratingRepo).save(ratingArgumentCaptor.capture());
        FilmRating savedRating = ratingArgumentCaptor.getValue();

        //then
        assertEquals(3, savedRating.getRating());
    }

    @Test
    void shouldFindRatingByFilmAndUser() {
        //when
        underTest.findRatingByFilmAndUser(film, user);
        //then
        verify(ratingRepo).findRatingByFilmAndUser(film, user);
    }

    @Test
    void deleteFilmRatingByFilmAndUser() {
        //when
        underTest.deleteFilmRatingByFilmAndUser(film, user);
        //then
        verify(ratingRepo).deleteFilmRatingByFilmAndUser(film, user);
    }

    @Test
    void refreshRating() {
        //given
        ArgumentCaptor<FilmRating> ratingArgumentCaptor = ArgumentCaptor.forClass(FilmRating.class);

        //when
        FilmRating newFilmRating = new FilmRating(user, film, 4);
        ratingRepo.save(newFilmRating);
        verify(ratingRepo).save(ratingArgumentCaptor.capture());

        underTest.refreshRating(film);

        //then
        assertEquals(4, ratingArgumentCaptor.getValue().getRating());
    }
}