package com.example.filmlib.app.service;

import com.example.filmlib.app.entity.Film;
import com.example.filmlib.app.repository.FilmRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmServiceImpl implements FilmService{
    private final FilmRepo filmRepo;
    @Autowired
    @Lazy
    public FilmServiceImpl(FilmRepo filmRepo) {
        this.filmRepo = filmRepo;
    }

    @Override
    public Film save(Film film) {
        return filmRepo.save(film);
    }

    @Override
    public List<Film> findAll() {
        return filmRepo.findAll();
    }
}
