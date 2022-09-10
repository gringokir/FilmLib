package com.example.filmlib.app.service.impl;

import com.example.filmlib.app.entity.User;
import com.example.filmlib.app.repository.UserRepo;
import com.example.filmlib.app.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    @Transactional
    public User loadUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public User save(User user) {
        return userRepo.save(user);
    }

}
