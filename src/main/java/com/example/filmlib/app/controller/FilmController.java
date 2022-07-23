package com.example.filmlib.app.controller;

import com.example.filmlib.app.entity.Film;
import com.example.filmlib.app.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/films")
public class FilmController {
    private final FilmService filmService;
    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public String showAll(Model model) {
        model.addAttribute("films", filmService.findAll());
        return "films";
    }

    @PostMapping
    public String add(@RequestParam String title, @RequestParam String genre, Model model) {
        Film film = new Film(title, genre);
        filmService.save(film);

        Iterable<Film> films = filmService.findAll();
        model.addAttribute("films", films);

        return "films";
    }

}
