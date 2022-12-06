package com.filmlib.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FilmRatingKey implements Serializable {
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "film_id")
    private Long filmId;

    public FilmRatingKey(Long userId, Long filmId) {
        this.userId = userId;
        this.filmId = filmId;
    }

    public FilmRatingKey() {
    }

    public Long getUserId() {
        return this.userId;
    }

    public Long getFilmId() {
        return this.filmId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setFilmId(Long filmId) {
        this.filmId = filmId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmRatingKey that = (FilmRatingKey) o;
        return Objects.equals(userId, that.userId) && Objects.equals(filmId, that.filmId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, filmId);
    }
}
