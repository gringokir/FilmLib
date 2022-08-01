package com.example.filmlib.app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "artists")
@Setter
@Getter
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String placeOfBirth;

    @ElementCollection(targetClass = ArtistJob.class)
    @CollectionTable(name = "artist_job", joinColumns = @JoinColumn(name = "artist_id"))
    @Enumerated(EnumType.STRING)
    private Set<ArtistJob> job;
}
