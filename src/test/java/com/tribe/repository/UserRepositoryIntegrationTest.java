package com.tribe.repository;

import com.tribe.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryIntegrationTest {
    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:latest");

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setup() {
        userRepository.deleteAll();
    }


    @Test
    void shouldEstablishConnectionToPostgreSQLTestContainer() {
        assertThat(postgreSQLContainer.isCreated()).isTrue();
        assertThat(postgreSQLContainer.isRunning()).isTrue();
    }

    @Test
    void shouldReturnEmptyUsersListInitially() {
        // Act
        List<User> users = userRepository.findAll();

        // Assert
        assertThat(users).isEmpty();
    }

    @Test
    void shouldSaveUsers() {
        // Arrange
        User user1 = new User("Alice", "Smith", "alice", "alice@example.com", "pass1");
        User user2 = new User("Bob", "Johnson", "bob", "bob@example.com", "pass2");
        User user3 = new User("Charlie", "Brown", "charlie", "charlie@example.com", "pass3");
        List<User> usersToBeSaved = List.of(user1, user2, user3);

        // Act
        userRepository.saveAll(usersToBeSaved);
        List<User> savedUsers = userRepository.findAll();

        // Assert
        assertThat(savedUsers).hasSize(usersToBeSaved.size());
        assertThat(savedUsers).extracting(User::getUsername).contains("alice", "bob", "charlie");
    }

    @Test
    void shouldFindUserByUsername() {
        // Arrange
        User user = new User("Alice", "Smith", "alice", "alice@example.com", "pass1");
        userRepository.save(user);

        // Act
        User foundUser = userRepository.findByUsername("alice");

        // Assert
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getUsername()).isEqualTo("alice");
    }

    @Test
    void shouldFindUserByEmail() {
        // Arrange
        User user = new User("Bob", "Johnson", "bob", "bob@example.com", "pass2");
        userRepository.save(user);

        // Act
        User foundUser = userRepository.findByEmail("bob@example.com");

        // Assert
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getEmail()).isEqualTo("bob@example.com");
    }

    @Test
    void shouldUpdateUser() {
        // Arrange
        User user = new User("Charlie", "Brown", "charlie", "charlie@example.com", "pass3");
        userRepository.save(user);
        user.setEmail("newcharlie@example.com");
        user.setPassword("newpass");

        // Act
        userRepository.save(user);
        User updatedUser = userRepository.findByUsername("charlie");

        // Assert
        assertThat(updatedUser).isNotNull();
        assertThat(updatedUser.getEmail()).isEqualTo("newcharlie@example.com");
        assertThat(updatedUser.getPassword()).isEqualTo("newpass");
    }

    @Test
    void shouldDeleteUser() {
        // Arrange
        User user = new User("Alice", "Smith", "alice", "alice@example.com", "pass1");
        userRepository.save(user);

        // Act
        userRepository.delete(user);
        User deletedUser = userRepository.findByUsername("alice");

        // Assert
        assertThat(deletedUser).isNull();
    }

    @Test
    void shouldCheckIfUserExistsByUsername() {
        // Arrange
        User user = new User("Charlie", "Brown", "charlie", "charlie@example.com", "pass3");
        userRepository.save(user);

        // Act
        boolean userExists = userRepository.existsByUsername("charlie");

        // Assert
        assertThat(userExists).isTrue();
    }

    @Test
    void shouldReturnNullWhenUserNotFoundByUsername() {
        // Act
        User foundUser = userRepository.findByUsername("nonexistent");

        // Assert
        assertThat(foundUser).isNull();
    }
}
