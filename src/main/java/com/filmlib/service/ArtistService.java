package com.filmlib.service;

import com.filmlib.entity.Artist;

import java.util.List;

public interface ArtistService {
    Artist save(Artist artist);

    List<Artist> findAll();

    Artist findById(Long id);

    void delete(Long id);
}
