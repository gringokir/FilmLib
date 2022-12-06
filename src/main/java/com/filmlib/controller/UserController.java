package com.filmlib.controller;

import com.auth0.jwt.algorithms.Algorithm;
import com.filmlib.entity.Role;
import com.filmlib.entity.User;
import com.filmlib.repository.UserRepo;
import com.filmlib.util.SecurityUtil;
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

@Controller
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:8081")
//@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    private final UserRepo userRepo;

    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping
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

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith(BEARER)) {
            try {
                String refreshToken = authorizationHeader.substring(BEARER.length());
                Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY.getBytes());
                User user = userRepo.findByUsername(getUsername(refreshToken, algorithm));

                String accessToken = createAccessToken(algorithm, user);

                setTokensToResponse(response, refreshToken, accessToken);
            } catch (Exception e) {
                SecurityUtil.tokenException(response, e);
            }
        } else {
            throw new RuntimeException("refresh token is missing");
        }
    }
}
