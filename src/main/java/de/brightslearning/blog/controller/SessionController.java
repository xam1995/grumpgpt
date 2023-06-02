package de.brightslearning.blog.controller;

import de.brightslearning.blog.model.Login;
import de.brightslearning.blog.model.Session;
import de.brightslearning.blog.model.User;
import de.brightslearning.blog.service.SessionService;
import de.brightslearning.blog.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.Instant;
import java.util.Optional;

@Controller
public class SessionController {

    private UserService userService;
    private SessionService sessionService;

    @Autowired
    public SessionController(SessionService sessionService, UserService userService) {
        this.sessionService = sessionService;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("login", new Login("", ""));
        return "login";
    }

    @PostMapping("/login")
    public String login(HttpServletResponse response, @ModelAttribute("login") Login login) {
        Optional<User> optionalUser = userService.getByUsernameAndPassword(login.getUsername(), login.getPassword());

        if (optionalUser.isPresent()) {
            Session session = new Session(optionalUser.get(), Instant.now().plusSeconds(7 * 24 * 60 * 60)); //expires one week from now
            sessionService.save(session);

            //store the session ID in a cookie to keep the username secret
            Cookie cookie = new Cookie("sessionId", session.getId());
            response.addCookie(cookie);

            // Login successful
            return "redirect:/";
        }

        return "login";
    }

    @PostMapping("/logout")
    public String logout(@CookieValue(value = "sessionId", defaultValue = "") String sessionId, HttpServletResponse response) {
        Optional<Session> optionalSession = sessionService.findByIdAndExpiresAt(sessionId, Instant.now());
        optionalSession.ifPresent(session -> sessionService.delete(session));

        Cookie cookie = new Cookie("sessionId", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String loggingout() {
        return "redirect:/";
    }
}
