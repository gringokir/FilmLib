package com.filmlib.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.filmlib.entity.Role;
import com.filmlib.entity.User;
import com.filmlib.entity.Views;
import com.filmlib.repository.UserRepo;
import com.filmlib.service.UserService;
import com.filmlib.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.filmlib.util.SecurityUtil.*;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {
    private final UserRepo userRepo;
    private final UserService userService;

    public UserController(UserRepo userRepo, UserService userService) {
        this.userRepo = userRepo;
        this.userService = userService;
    }

    @GetMapping("/user/{username}")
    @JsonView(Views.User.class)
    public User getUser(@PathVariable String username){
        log.info("User: \"" + username +"\" was called");
        return (User) userService.loadUserByUsername(username);
    }

    /*@GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userRepo.findAll());
        return "userList";
    }

    @GetMapping("{user}")
    public String editUser(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Arrays.asList(Role.values()));
        return "userEdit";
    }

    @PostMapping
    public String userSave(
            @RequestParam("userId") User user,
            @RequestParam String username,
            @RequestParam Map<String, String> userRoles
    ) {
        user.setUsername(username);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : userRoles.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepo.save(user);
        return "redirect:/users";
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteUser(@PathVariable Long id) {
        log.info("User with id: \"" + id +"\" was deleted");
    }*/

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith(BEARER)) {
            try {
                String refreshToken = authorizationHeader.substring(BEARER.length());
                User user = userRepo.findByUsername(getUsername(refreshToken));

                String accessToken = createAccessToken(user);

                setTokensToResponse(response, refreshToken, accessToken);
            } catch (Exception e) {
                SecurityUtil.tokenException(response, e);
            }
        } else {
            throw new RuntimeException("refresh token is missing");
        }
    }
}
