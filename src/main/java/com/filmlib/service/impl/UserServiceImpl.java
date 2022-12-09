package com.filmlib.service.impl;

import com.filmlib.entity.User;
import com.filmlib.repository.UserRepo;
import com.filmlib.security.PasswordConfig;
import com.filmlib.service.UserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final PasswordConfig passwordConfig;

    public UserServiceImpl(UserRepo userRepo, PasswordConfig passwordConfig) {
        this.userRepo = userRepo;
        this.passwordConfig = passwordConfig;
    }

    @Override
    @Transactional
    public User loadUserByUsername(String username) {
        User user = userRepo.findByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException(String.format("User: %s not found", username));
        }
        return user;
    }

    @Override
    public User save(User user) {
        user.setPassword(passwordConfig.passwordEncoder().encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUser(Long id) {
        return userRepo.getReferenceById(id);
    }

    @Override
    public void delete(Long id) {
        User user = userRepo.getReferenceById(id);
        user.getWatchedFilms().clear();
        user.getFilmRatings().clear();
        user.getRoles().clear();
        userRepo.deleteById(id);
    }
}
