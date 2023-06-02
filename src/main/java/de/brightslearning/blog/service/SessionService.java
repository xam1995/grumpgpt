package de.brightslearning.blog.service;

import de.brightslearning.blog.model.Session;
import de.brightslearning.blog.persistance.SessionDAO;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Service
public class SessionService {
    private SessionDAO sessionDAO;

    public SessionService(SessionDAO sessionDAO) {
        this.sessionDAO = sessionDAO;
    }

    public Optional<Session> findByIdAndExpiresAt(String sessionId, Instant instant) {
        return this.sessionDAO.findByIdAndExpiresAtAfter(sessionId, instant);
    }

    public void save(Session session) {
        this.sessionDAO.save(session);
    }

    public void delete(Session session) {
       this.sessionDAO.delete(session);
    }
}
