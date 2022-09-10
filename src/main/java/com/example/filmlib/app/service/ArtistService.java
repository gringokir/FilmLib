package com.example.filmlib.app.service;

import com.example.filmlib.app.entity.Artist;
import com.example.filmlib.app.entity.Film;

import java.util.List;

public interface ArtistService {
    Artist save(Artist artist);

    List<Artist> findAll();

    Artist findById(Long id);

    void delete(Long id);
}
