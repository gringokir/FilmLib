package com.example.filmlib.app.entity;

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
    private Long id;
    private String title;
    private int yearOfCreation;

    @ElementCollection(targetClass = Genre.class)
    @CollectionTable(name = "film_genre", joinColumns = @JoinColumn(name = "film_id"))
    @Enumerated(EnumType.STRING)
    private Set<Genre> genre = new HashSet<>();

    private double rating;
    @OneToMany(mappedBy = "film")
    private Set<FilmRating> filmRatings = new HashSet<>();

    @ManyToMany(mappedBy = "watchedFilms")
    private Set<User> users = new HashSet<>();                      //user which watched this film

    public Film(String title, int yearOfCreation) {
        this.title = title;
        this.yearOfCreation = yearOfCreation;
        this.rating = 0.0;
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
