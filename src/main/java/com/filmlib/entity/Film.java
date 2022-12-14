package com.filmlib.entity;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "films")
@Getter
@Setter
@NoArgsConstructor
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView({Views.Film.class, Views.User.class})
    private Long id;
    @JsonView({Views.Film.class, Views.User.class})
    private String title;
    @JsonView(Views.Film.class)
    private int yearOfCreation;
    @JsonView(Views.Film.class)
    private String posterUrl;

    @ElementCollection(targetClass = Genre.class)
    @CollectionTable(name = "film_genre", joinColumns = @JoinColumn(name = "film_id"))
    @Enumerated(EnumType.STRING)
    @JsonView(Views.Film.class)
    private Set<Genre> genre = new HashSet<>();

    @JsonView(Views.Film.class)
    private double rating;
    @OneToMany(mappedBy = "film")
    private Set<FilmRating> filmRatings = new HashSet<>();

    @ManyToMany(mappedBy = "watchedFilms")
    private Set<User> users = new HashSet<>();                      //user which watched this film

    @ManyToMany(mappedBy = "films")
    private Set<Artist> artists = new HashSet<>();

    public Film(String title, int yearOfCreation) {
        this.title = title;
        this.yearOfCreation = yearOfCreation;
        this.rating = 0.0;
    }

    // TODO clear ratings
    public void clearUsersAndArtists() {
        users.forEach(user -> user.getWatchedFilms().remove(this));
        artists.forEach(artist -> artist.getFilms().remove(this));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return getId().equals(film.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
