package com.example.blogCreator.controllers;

import com.example.blogCreator.models.services.BlogService;
import com.example.blogCreator.models.services.SessionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    final SessionService sessionService;
    final BlogService blogService;

    public MainController(SessionService sessionService, BlogService blogService) {
        this.sessionService = sessionService;
        this.blogService = blogService;
    }
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("userObject", sessionService);
        model.addAttribute("blogs", blogService.getAllBlogs());

        return "index";
    }
}
