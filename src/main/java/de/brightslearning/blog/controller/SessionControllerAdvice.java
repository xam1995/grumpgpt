package de.brightslearning.blog.controller;

import de.brightslearning.blog.model.Session;
import de.brightslearning.blog.model.User;
import de.brightslearning.blog.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.time.Instant;
import java.util.Optional;

@ControllerAdvice
public class SessionControllerAdvice {
    private SessionService sessionService;

    @Autowired
    public SessionControllerAdvice(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @ModelAttribute("sessionUser")
    public User sessionUser(@CookieValue(value = "sessionId", defaultValue = "") String sessionId) {
        if (!sessionId.isEmpty()) {
            Optional<Session> optionalSession = sessionService.findByIdAndExpiresAt(
                    sessionId, Instant.now());
            if (optionalSession.isPresent()) {
                Session session = optionalSession.get();

                // new expiresAt value for the current session
                session.setExpiresAt(Instant.now().plusSeconds(7 * 24 * 60 * 60));
                sessionService.save(session);

                return session.getUser();
            }
        }
        return null;
    }
}
