package de.brightslearning.blog.persistance;

import de.brightslearning.blog.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.Optional;

public interface SessionDAO extends JpaRepository<Session, Integer> {
    Optional<Session> findByIdAndExpiresAtAfter(String id, Instant expiresAt);
}
