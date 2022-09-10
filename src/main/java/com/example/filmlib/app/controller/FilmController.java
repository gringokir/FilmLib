package com.example.filmlib.app.controller;

import com.example.filmlib.app.entity.*;
import com.example.filmlib.app.service.ArtistService;
import com.example.filmlib.app.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/films")
public class FilmController {
    private final FilmService filmService;
    private final ArtistService artistService;

    @Autowired
    public FilmController(FilmService filmService, ArtistService artistService) {
        this.filmService = filmService;
        this.artistService = artistService;
    }

    @GetMapping
    public String showAll(Model model, Authentication auth) {
        User user = (User) auth.getPrincipal();
        List<Film> films = filmService.findAll();
        films.sort(Comparator.comparing(Film::getId));

        model.addAttribute("user", user);
        model.addAttribute("film", new Film());
        model.addAttribute("films", films);
        model.addAttribute("genres", Genre.values());
        return "films";
    }

    @GetMapping("/film")
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
    }

}
