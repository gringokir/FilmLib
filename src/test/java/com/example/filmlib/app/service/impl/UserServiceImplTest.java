package com.example.filmlib.app.service.impl;

import com.example.filmlib.app.entity.User;
import com.example.filmlib.app.repository.UserRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    private UserServiceImpl underTest;
    @Mock
    private UserRepo userRepo;

    @BeforeEach
    void setUp() {
        underTest = new UserServiceImpl(userRepo);
    }

    @Test
    void canLoadUserByUsername() {
        //given
        String username = "username";
        //when
        User user = underTest.loadUserByUsername(username);
        //then
        verify(userRepo).findByUsername(username);
    }

    @Test
    void canSave() {
        //given
        User user = new User();
        //when
        underTest.save(user);
        //then
        verify(userRepo).save(user);
    }
}