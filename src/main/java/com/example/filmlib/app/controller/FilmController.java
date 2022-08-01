package com.example.filmlib.app.controller;

import com.example.filmlib.app.entity.Film;
import com.example.filmlib.app.entity.Genre;
import com.example.filmlib.app.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("film", new Film());
        model.addAttribute("films", filmService.findAll());
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

    @PostMapping
    public String add(@ModelAttribute("film") Film film, Model model) {
        filmService.save(film);

        Iterable<Film> films = filmService.findAll();
        model.addAttribute("films", films);
        return "redirect:/films";
    }

}
