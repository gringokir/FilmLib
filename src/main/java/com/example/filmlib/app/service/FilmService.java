package com.example.filmlib.app.service;

import com.example.filmlib.app.entity.Artist;
import com.example.filmlib.app.entity.Film;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

public interface FilmService {
    Film save(Film film);

    Film findByID(Long id);

    List<Film> findAll();

    @Transactional
    Film save(Film film, Set<Artist> artists);

    void delete(Long id);
}
