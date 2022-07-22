package com.example.filmlib.app.repository;

import com.example.filmlib.app.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepo extends JpaRepository<Film, Long> {
}
