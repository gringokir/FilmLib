package com.filmlib.entity;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class FilmRating {
    @EmbeddedId
    private FilmRatingKey id;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("filmId")
    @JoinColumn(name = "film_id")
    @JsonView(Views.User.class)
    private Film film;

    @JsonView(Views.User.class)
    private int rating;

    public FilmRating(User user, Film film, int rating) {
        this.user = user;
        this.film = film;
        this.rating = rating;

        setId(new FilmRatingKey(user.getId(), film.getId()));
    }

    public FilmRating() {
    }

    public FilmRatingKey getId() {
        return this.id;
    }

    public User getUser() {
        return this.user;
    }

    public Film getFilm() {
        return this.film;
    }

    public int getRating() {
        return this.rating;
    }

    public void setId(FilmRatingKey id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmRating that = (FilmRating) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
