package de.brightslearning.blog.persistance;

import de.brightslearning.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserDAO extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    Optional<User> getByUsernameAndPassword(String username, String password);
    User getByEmail(String email);
}
