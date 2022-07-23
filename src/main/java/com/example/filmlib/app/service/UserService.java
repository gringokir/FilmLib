package com.example.filmlib.app.service;

import com.example.filmlib.app.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(User user);
}
