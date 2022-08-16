package com.example.filmlib.app.entity;

import javax.persistence.*;

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
    private Film film;

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
}
