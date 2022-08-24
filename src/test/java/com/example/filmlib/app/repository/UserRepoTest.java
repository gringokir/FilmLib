package com.example.filmlib.app.repository;

import com.example.filmlib.app.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepoTest {
    @Autowired
    private UserRepo underTest;

    @Test
    void itShouldFindUserByUsername() {
        //given
        String username = "john";
        User user = new User(username, "123456", true);
        underTest.save(user);

        //when
        User byUsername = underTest.findByUsername(username);

        //then
        assertThat(byUsername).isEqualTo(user);
    }

    @Test
    void itShouldNotFindUserByUsername() {
        //given
        String username = "john";

        //when
        User byUsername = underTest.findByUsername(username);

        //then
        assertThat(byUsername).isNull();
    }
}