package com.jrsmiffy.jara3.userservice.repository;

import com.jrsmiffy.jara3.userservice.model.AppUser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    UserRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    @DisplayName("Should Find User By Username")
    void shouldFindUserByUsername(){ // this is a redundant test but I have included it as an example
        // Given
        AppUser user = underTest.save(new AppUser("username", "password"));
        Optional<AppUser> expected = Optional.of(user);
        // When
        Optional<AppUser> actual = underTest.findByUsername("username");
        // Then
        assertThat(actual).isEqualTo(expected);
    }

}
