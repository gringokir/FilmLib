package com.example.filmlib.app.repository;

import com.example.filmlib.app.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepo extends JpaRepository<Artist, Long> {
}
