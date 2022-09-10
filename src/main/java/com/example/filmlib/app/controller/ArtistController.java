package com.example.filmlib.app.controller;

import com.example.filmlib.app.entity.Artist;
import com.example.filmlib.app.service.ArtistService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/artists")
public class ArtistController {
    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }


    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String createArtist(@ModelAttribute("artist") Artist artist) {
        artistService.save(artist);
        return "redirect:/films/add-film";
    }

    @GetMapping("/artist")
    public String artistPage(@RequestParam Long id, Model model) {
        Artist artist = artistService.findById(id);
        model.addAttribute("artist", artist);
        model.addAttribute("films", artist.getFilms());
        return "artistPage";
    }

    @GetMapping("/delete")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteArtist(@RequestParam Long id) {
        artistService.delete(id);
        return "redirect:/films";
    }
}
