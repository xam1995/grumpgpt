package de.brightslearning.blog.controller;

import de.brightslearning.blog.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BlogController {

    //public BlogService bs;
    
    @GetMapping("/")
    public String showHomePage(Model model){
       return "/home";
    }

//    @PostMapping("/createEntry")
//    public String createEntry(@RequestParam){
//
//      bs.createEntry();
//
//     return "/";
//     }

}
