package com.example.filmlib.app.service.impl;

import com.example.filmlib.app.entity.Film;
import com.example.filmlib.app.repository.FilmRepo;
import com.example.filmlib.app.service.FilmService;
import com.example.filmlib.app.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmServiceImpl implements FilmService {
    private final FilmRepo filmRepo;
    private final RatingService ratingService;

    @Autowired
    public FilmServiceImpl(FilmRepo filmRepo, RatingService ratingService) {
        this.filmRepo = filmRepo;
        this.ratingService = ratingService;
    }

    @Override
    public Film save(Film film) {
        return filmRepo.save(film);
    }

    @Override
    public Film findByID(Long id) {
        return filmRepo.findById(id).orElseThrow();
    }

    @Override
    public List<Film> findAll() {
        List<Film> films = filmRepo.findAll();
        films.forEach(ratingService::refreshRating);
        return films;
    }
}
