package com.example.filmlib.app.service.impl;

import com.example.filmlib.app.entity.Artist;
import com.example.filmlib.app.entity.Film;
import com.example.filmlib.app.repository.FilmRepo;
import com.example.filmlib.app.service.ArtistService;
import com.example.filmlib.app.service.FilmService;
import com.example.filmlib.app.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class FilmServiceImpl implements FilmService {
    private final FilmRepo filmRepo;
    private final RatingService ratingService;
    private final ArtistService artistService;

    @Autowired
    public FilmServiceImpl(FilmRepo filmRepo, RatingService ratingService, ArtistService artistService) {
        this.filmRepo = filmRepo;
        this.ratingService = ratingService;
        this.artistService = artistService;
    }

    @Override
    @Transactional
    public Film findByID(Long id) {
        return filmRepo.findById(id).orElseThrow();
    }

    @Override
    public List<Film> findAll() {
        List<Film> films = filmRepo.findAll();
        films.forEach(ratingService::refreshRating);
        return films;
    }

    @Override
    public Film save(Film film) {
        return filmRepo.save(film);
    }

    @Transactional
    public Film save(Film film, Set<Artist> artists) {
        filmRepo.save(film);

        for (Artist artist : artists) {
            artist.getFilms().add(film);
            artistService.save(artist);
        }
        return film;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Film film = findByID(id);
        film.clearUsersAndArtists();
        filmRepo.delete(film);
    }
}
