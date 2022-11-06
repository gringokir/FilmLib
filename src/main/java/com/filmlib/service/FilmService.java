package com.filmlib.service;

import com.filmlib.entity.Artist;
import com.filmlib.entity.Film;
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
