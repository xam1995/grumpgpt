package de.brightslearning.blog.controller;

import de.brightslearning.blog.model.BlogEntry;
import de.brightslearning.blog.model.Comment;
import de.brightslearning.blog.model.Session;
import de.brightslearning.blog.model.User;
import de.brightslearning.blog.service.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
public class BlogController {
    private UserService userService;
    private SessionService sessionService;
    private BlogService blogService;
    private CommentService commentService;

    @Autowired
    public BlogController(
            UserService userService,
            SessionService sessionService,
            BlogService blogService,
            CommentService commentService
    ) {
        this.userService = userService;
        this.sessionService = sessionService;
        this.blogService = blogService;
        this.commentService = commentService;
    }

    @GetMapping("/")
    public String showHomePage(Model model, @ModelAttribute("sessionUser") User sessionUser) {

        List<BlogEntry> blogs = blogService.findAll();
        model.addAttribute("blogs", blogs);
        model.addAttribute("user", sessionUser);

        return "/home";
    }

    @GetMapping("/blog/{id}")
    public String showBlog(Model model, HttpServletResponse response, @PathVariable(name = "id") String id, @ModelAttribute("sessionUser") User sessionUser) {

        //Get data from our services, later our database
        Optional<BlogEntry> blog = blogService.getById(Integer.parseInt(id));
        Optional<User> author = userService.getUserById(blog.get().getAuthor_id());
        List<Comment> comments = commentService.findAllForOneBlog(blog.get().getId());

        //add empty comment
        Comment empytyComment = new Comment();
        model.addAttribute("newComment", empytyComment);

        //Data is sent to thymeleaf via package
        model.addAttribute("user", sessionUser);
        model.addAttribute("blog", blog.orElse(null));
        model.addAttribute("author", author.orElse(null));
        model.addAttribute("comments", comments);

        return "/fullEntry";
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model, @ModelAttribute("sessionUser") User sessionUser) {

        User emptyUser = new User();

        //pass empty user to thymeleaf engine, so it can be used as template in registration form
        model.addAttribute("newUser", emptyUser);

        //redirect to home page if somebody's already logged in
        if (sessionUser != null) {
            return "redirect:/";
        }

        return "/register";
    }

    @PostMapping("/registeruser")
    public String registerUser(@ModelAttribute("newUser") User user) {
        userService.save(user);
        return "redirect:/welcome";
    }


    @GetMapping("welcome")
    public String showWelcomePage(Model model){
        return "/welcome";
    }

    @GetMapping("/new")
    public String showNewEntry(Model model, @ModelAttribute("sessionUser") User sessionUser) {

        BlogEntry blogEntry = new BlogEntry();

        model.addAttribute("blog", blogEntry);
        model.addAttribute("user", sessionUser);

        //allow logged in admins to create new post
        if (sessionUser != null && sessionUser.isAdmin()) {
            return "/post";
        }
        //redirect to home page if user doesn't have privileges or isn't logged in
        return "redirect:/";
    }

    // id stands for blog that will be edited
    @GetMapping("/edit/{id}")
    public String editPost(Model model, HttpServletResponse response, @PathVariable(name = "id") String id, @ModelAttribute("sessionUser") User sessionUser) {
        Optional<User> optionalUser = userService.getByUsernameAndPassword(sessionUser.getUsername(), sessionUser.getPassword());
        Optional<BlogEntry> blog = blogService.getById(Integer.valueOf(id));

        model.addAttribute("user", optionalUser.orElse(null));
        model.addAttribute("blog", blog.orElse(null));

        if (optionalUser.isPresent() && optionalUser.get().isAdmin()) {
            return "/edit";
        }

        return "redirect:/";
    }


    @PostMapping("/saveblog")
    public String save(@ModelAttribute("blog") BlogEntry blog, @ModelAttribute("sessionUser") User sessionUser) {
        blog.setAuthor_id(sessionUser.getId());
        if (blog.getTimestamp() == null) {
            blog.setTimestamp(LocalDateTime.now());
        }
        blogService.save(blog);
        return "redirect:/";

    }

    @PostMapping("/savecomment")
    public String save(@ModelAttribute("comment") Comment comment, @ModelAttribute("blog") BlogEntry blog) {
        commentService.save(comment);
        return "redirect:/blog/" + blog.getId();
    }

    @PostMapping("/deleteblog")
    public String deleteBlog(@ModelAttribute("blog") BlogEntry blog) {
        blogService.delete(blog.getId());

        return "redirect:/";
    }

}
