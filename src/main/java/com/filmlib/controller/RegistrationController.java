package com.filmlib.controller;

import com.filmlib.entity.Role;
import com.filmlib.entity.User;
import com.filmlib.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@Slf4j
@RequestMapping("/api/registration")
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> addUser(User user) throws UsernameNotFoundException {
        UserDetails userFromDb = null;
        try {
            userFromDb = userService.loadUserByUsername(user.getUsername());
        } catch (UsernameNotFoundException e) {
            log.info("User with username: " + user.getUsername() + " is not found, proceeding with creation...");
        }

        if(userFromDb != null){
            log.info("A user with username: " + user.getUsername() + " already exists!");
            return ResponseEntity.status(HttpStatus.CONFLICT).body("A user with this username already exists!");
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userService.save(user);

        log.info("A user with username: " + user.getUsername() + " is created.");

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
