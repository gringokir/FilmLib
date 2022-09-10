package com.example.filmlib.app.service.impl;

import com.example.filmlib.app.entity.Film;
import com.example.filmlib.app.repository.FilmRepo;
import com.example.filmlib.app.service.ArtistService;
import com.example.filmlib.app.service.RatingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FilmServiceImplTest {
//    @Autowired
    @InjectMocks
    private FilmServiceImpl underTest;
    @Mock
    private FilmRepo filmRepo;
    @Mock
    private RatingService ratingService;
    @Mock
    private ArtistService artistService;
    private Film film;

    @BeforeEach
    void setUp() {
        underTest = new FilmServiceImpl(filmRepo, ratingService, artistService);
    }

    @Test
    void canSave() {
        //when
        underTest.save(film);
        //then
        verify(filmRepo).save(film);
    }

    @Test
    void canFindByID() {
        //given
        film = new Film("Titanic", 1983);
        film.setId(1L);
        filmRepo.save(film);
        Long filmId = film.getId();
        //when
        when(filmRepo.findById(filmId)).thenReturn(Optional.of(film));
        //then
        assertThat(underTest.findByID(filmId)).isEqualTo(film);
    }

    @Test
    void canFindAll() {
        //when
        underTest.findAll();
        //then
        verify(filmRepo).findAll();
    }
}