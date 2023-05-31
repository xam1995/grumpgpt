package de.brightslearning.blog.controller;

import de.brightslearning.blog.model.Session;
import de.brightslearning.blog.model.User;
import de.brightslearning.blog.service.FakeSessionService;
import de.brightslearning.blog.service.FakeUserService;
import de.brightslearning.blog.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.Instant;
import java.util.Optional;

@Controller
public class BlogController {
    private UserService userService;

    private FakeSessionService fakeSessionService;

    //public BlogService bs;
    @Autowired
    public BlogController(UserService userService, FakeSessionService fakeSessionService) {
        this.userService = userService;
        this.fakeSessionService = fakeSessionService;
    }

    @GetMapping("/")
    public String showHomePage(Model model){
       return "/home";
    }

    @GetMapping("/login")
    public String showLogIn(Model model, HttpServletResponse response){
        Optional<User> optionalFakeUser = userService.getByUsernameAndPassword("staaani12", "12345678");
        model.addAttribute("user", optionalFakeUser.orElse(null));

        if (optionalFakeUser.isPresent()) {
            Session newSession = new Session(optionalFakeUser.get(), Instant.now().plusSeconds(7*24*60*60));
            fakeSessionService.save(newSession);

            Cookie cookie = new Cookie("sessionId", newSession.getId());
            response.addCookie(cookie);
            return "redirect:/"; // if login was successful
        }

        return "/login"; // if login was not successful

    }

    @GetMapping("/logout")
    public String logout(Model model, HttpServletResponse response){

        Cookie logoutCookie = new Cookie("sessionId", "");
        response.addCookie(logoutCookie);

        model.addAttribute("user", null);

        return "redirect:/";
    }

    @GetMapping("/blog")
    public String showBlog(Model model, HttpServletResponse response){

        Optional<User> optionalFakeUser = userService.getByUsernameAndPassword("staaani123", "12345678");
        model.addAttribute("user", optionalFakeUser.orElse(null));

        if (optionalFakeUser.isPresent()) {

        }
        return "/fullEntry";
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model){
        return "/register";
    }

    @GetMapping("/new")
    public String showNewEntry(Model model){
        return "/post";
    }

    @GetMapping("/edit")
    public String editPost(Model model, HttpServletResponse response){
        Optional<User> optionalFakeUser = userService.getByUsernameAndPassword("steven123", "12345678");
        model.addAttribute("user", optionalFakeUser.orElse(null));

        if (optionalFakeUser.isPresent() && optionalFakeUser.get().isAdmin()) {

                Session newSession = new Session(optionalFakeUser.get(), Instant.now().plusSeconds(7 * 24 * 60 * 60));
                fakeSessionService.save(newSession);

                Cookie cookie = new Cookie("sessionId", newSession.getId());
                response.addCookie(cookie);

                return "/edit";

        }
        return "redirect:/";
   }


//    @PostMapping("/createEntry")
//    public String createEntry(@RequestParam){
//
//      bs.createEntry();
//
//     return "/";
//     }
}
