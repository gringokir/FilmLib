package com.filmlib.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.filmlib.entity.*;
import com.filmlib.service.ArtistService;
import com.filmlib.service.FilmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@Slf4j
@RequestMapping("/api/films")
public class FilmController {
    private final FilmService filmService;
    private final ArtistService artistService;

    @Autowired
    public FilmController(FilmService filmService, ArtistService artistService) {
        this.filmService = filmService;
        this.artistService = artistService;
    }

    @GetMapping
    @JsonView(Views.Film.class)
    public List<Film> all() {
        log.info("List of all films was called");
        return filmService.findAll();
    }

    @GetMapping("/film/{id}")
    @JsonView(Views.Film.class)
    public Film filmPage(@PathVariable Long id) {
        Film film = filmService.findByID(id);
        log.info("Film with title: \"" + film.getTitle() + "\" was called");
        return film;
    }

   /* @GetMapping("/film")
    public String filmPage(@RequestParam Long id, Model model) {
        Film film = filmService.findByID(id);
        model.addAttribute("film", film);
        model.addAttribute("artists", film.getArtists());
        model.addAttribute("genres", film.getGenre().toString());
        return "filmPage";
    }

    @GetMapping("/add-film")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addFilmPage(Model model) {
        model.addAttribute("genres", Genre.values());
        model.addAttribute("film", new Film());
        model.addAttribute("artists", artistService.findAll());
        model.addAttribute("artist", new Artist());
        model.addAttribute("artistJobs", ArtistJob.values());
        model.addAttribute("countries", Countries.allCountries());
        return "addFilm";
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addFilm(
            @ModelAttribute("film") Film film,
            @RequestParam(value = "Artists", required = false) Set<Artist> Artists
    ) {
        if (Artists == null) {
            filmService.save(film);
            return "redirect:/films";
        }
        filmService.save(film, Artists);
        return "redirect:/films";
    }

    @GetMapping("/delete")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteFilm(@RequestParam Long id) {
        filmService.delete(id);
        return "redirect:/films";
    }*/

}
