package com.example.blogCreator.controllers;

import com.example.blogCreator.models.forms.BlogForm;
import com.example.blogCreator.models.services.BlogService;
import com.example.blogCreator.models.services.PostService;
import com.example.blogCreator.models.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BlogController {

    private final PostService postService;
    private final SessionService sessionService;
    private final BlogService blogService;

    @Autowired
    public BlogController(PostService postService, SessionService sessionService, BlogService blogService) {
        this.postService = postService;
        this.sessionService = sessionService;
        this.blogService = blogService;
    }

    @GetMapping("/blog")
    public String blog (Model model){
        if(!sessionService.isLoggedIn()){
            return "redirect:/login";
        }
        model.addAttribute("blogForm", new BlogForm());
        return "addBlog";
    }
    @PostMapping("/blog")
    public String blog (@ModelAttribute("blogForm")BlogForm blogForm){
        blogService.addBlog(blogForm);
        return "redirect:/";
    }
    @GetMapping("/blog/{id}")
    public String blog (@PathVariable("id") int id, Model model){

        model.addAttribute("currentUser", sessionService.getUserEntity());
        model.addAttribute("posts", postService.getAllPostsByBlogId(id));
        model.addAttribute("blogData", blogService.getAllBlogData(id));

        return "/blog";
    }

}
