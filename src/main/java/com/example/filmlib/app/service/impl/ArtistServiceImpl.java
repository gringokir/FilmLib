package com.example.filmlib.app.service.impl;

import com.example.filmlib.app.entity.Artist;
import com.example.filmlib.app.repository.ArtistRepo;
import com.example.filmlib.app.service.ArtistService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepo artistRepo;

    public ArtistServiceImpl(ArtistRepo artistRepo) {
        this.artistRepo = artistRepo;
    }

    @Override
    public Artist save(Artist artist) {
        return artistRepo.save(artist);
    }

    @Override
    public List<Artist> findAll() {
        return artistRepo.findAll();
    }

    @Override
    public Artist findById(Long id) {
        return artistRepo.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Artist artist = findById(id);
        artist.clearFilms();
        artistRepo.delete(artist);
    }
}
