package com.example.filmlib.app.service;

import com.example.filmlib.app.entity.Film;

import java.util.List;

public interface FilmService {
    Film save(Film film);

    List<Film> findAll();
}
