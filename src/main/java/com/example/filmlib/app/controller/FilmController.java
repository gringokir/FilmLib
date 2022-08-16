package com.example.filmlib.app.controller;

import com.example.filmlib.app.entity.Film;
import com.example.filmlib.app.entity.Genre;
import com.example.filmlib.app.entity.User;
import com.example.filmlib.app.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/films")
public class FilmController {
    private final FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
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

    @GetMapping("{id}")
    public String filmPage(@PathVariable Long id, Model model) {
        model.addAttribute("title", filmService.findByID(id).getTitle());
        String genres = filmService.findByID(id).getGenre().toString();
        model.addAttribute("genres", genres);
        return "filmPage";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("film") Film film, Model model) {
        filmService.save(film);

        Iterable<Film> films = filmService.findAll();
        model.addAttribute("films", films);
        return "redirect:/films";
    }

}
