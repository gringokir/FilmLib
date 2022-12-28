package com.filmlib.controller;

import com.filmlib.entity.Role;
import com.filmlib.entity.User;
import com.filmlib.service.UserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;

@Controller
@RequestMapping("/api/registration")
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String registration() {
        return "registration";
    }

    @PostMapping
    public String addUser(User user, Model model) throws UsernameNotFoundException {
        org.springframework.security.core.userdetails.UserDetails userFromDb = null;
        try {
            userFromDb = userService.loadUserByUsername(user.getUsername());
        } catch (UsernameNotFoundException e){
            e.printStackTrace();
        }


        if (userFromDb != null) {
            model.addAttribute("message", "User exists!");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userService.save(user);

        return "redirect:/login";
    }
}
