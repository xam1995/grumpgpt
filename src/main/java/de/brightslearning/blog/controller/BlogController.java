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
import java.util.List;
import java.util.Optional;

@Controller
public class BlogController {
    private UserService userService;

    private FakeSessionService fakeSessionService;

    private BlogService blogService;
    private CommentService commentService;


    //public BlogService bs;
    @Autowired
    public BlogController(UserService userService, FakeSessionService fakeSessionService, BlogService blogService, CommentService commentService) {
        this.userService = userService;
        this.fakeSessionService = fakeSessionService;
        this.blogService = blogService;
        this.commentService = commentService;
    }

    @GetMapping("/")
    public String showHomePage(Model model){

        List<BlogEntry> blogs = blogService.findAll();
        model.addAttribute("blogs", blogs);



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

    @GetMapping("/blog/{id}")
    public String showBlog(Model model, HttpServletResponse response, @PathVariable(name = "id") String id){


        //Get data from our services, later our database
        BlogEntry blog = blogService.getById(Integer.parseInt(id));
        Optional<User> user = userService.getUserById(1);
        Optional<User> author = userService.getUserById(blog.getAuthor_id());
        List<Comment> comments =  commentService.findAllForOneBlog(blog.getId());

        //add empty comment
         Comment empytyComment = new Comment();
         model.addAttribute("newComment", empytyComment);



        //Data is sent to thymeleaf via package
        model.addAttribute("user", user.orElse(null));
        model.addAttribute("blog", blog);
        model.addAttribute("author", author.orElse(null));
        model.addAttribute("comments", comments );

        return "/fullEntry";
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model){
        Optional<User> user = userService.getByUsernameAndPassword("staaani123", "12345678");

        if(user.isPresent()){
            return "redirect:/";
        }

        return "/register";
    }

    @GetMapping("/new")
    public String showNewEntry(Model model){
        Optional<User> user = userService.getByUsernameAndPassword("steven456", "12345678");

        if (user.isPresent() && user.get().isAdmin()) {
            return "/post";
        }
        return "redirect:/";
    }

    // id stands for blog that will be edited
    @GetMapping("/edit/{id}")
    public String editPost(Model model, HttpServletResponse response, @PathVariable(name = "id") String id){
        Optional<User> optionalFakeUser = userService.getByUsernameAndPassword("steven123", "12345678");
        BlogEntry blog = blogService.getById(1);

        model.addAttribute("user", optionalFakeUser.orElse(null));
        model.addAttribute("blog", blog);

        if (optionalFakeUser.isPresent() && optionalFakeUser.get().isAdmin()) {

                Session newSession = new Session(optionalFakeUser.get(), Instant.now().plusSeconds(7 * 24 * 60 * 60));
                fakeSessionService.save(newSession);

                Cookie cookie = new Cookie("sessionId", newSession.getId());
                response.addCookie(cookie);

                return "/edit";
        }

        return "redirect:/";
   }



   @PostMapping("/saveblog")
    public String save(@ModelAttribute("blog")  BlogEntry blog){

        blogService.save(blog);
        return "redirect:/";

   }



   @PostMapping("/savecomment")
   public  String save(@ModelAttribute("comment")Comment comment, @ModelAttribute("blog") BlogEntry blog){
        commentService.save(comment);


        return "redirect:/blog/" + blog.getId();
   }

   @DeleteMapping("/deleteblog")
    public String deleteBlog(@ModelAttribute("blog") BlogEntry blog){
        blogService.delete(blog.getId());

        List<Comment> allCommentsOneBlog = commentService.findAllForOneBlog(blog.getId());

         for(Comment comment : allCommentsOneBlog){
             commentService.delete(comment.getId());
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
