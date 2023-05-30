package de.brightslearning.blog.service;

import de.brightslearning.blog.model.Session;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Service
public class FakeSessionService {
    private List<Session> fakeSessions = new ArrayList<>();

    public Optional<Session> findByIdAndExpiresAt(String sessionId, Instant instant) {
        return this.fakeSessions.stream()
                .findAny()
                .filter(session ->
                        session.getId() == sessionId
                                && session.getExpiresAt().isAfter(instant));
    }

    public void save(Session session) {
        this.fakeSessions.add(session);
    }
}
