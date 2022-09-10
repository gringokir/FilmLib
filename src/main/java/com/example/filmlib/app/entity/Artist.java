package com.example.filmlib.app.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "artists")
@Setter
@Getter
@NoArgsConstructor
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String birthCountry;

    @ElementCollection(targetClass = ArtistJob.class)
    @CollectionTable(name = "artist_job", joinColumns = @JoinColumn(name = "artist_id"))
    @Enumerated(EnumType.STRING)
    private Set<ArtistJob> job = new HashSet<>();

    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinTable(
            name = "artists_in_films",
            joinColumns = @JoinColumn(name = "artist_id"),
            inverseJoinColumns = @JoinColumn(name = "film_id")
    )
    private Set<Film> films = new HashSet<>();

    public Artist(String name, String birthCountry) {
        this.name = name;
        this.birthCountry = birthCountry;
    }

    public void clearFilms() {
        films.forEach(film -> film.getArtists().remove(this));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return getId().equals(artist.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
