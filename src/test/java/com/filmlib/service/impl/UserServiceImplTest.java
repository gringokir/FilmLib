package com.filmlib.service.impl;

import com.filmlib.entity.User;
import com.filmlib.repository.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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