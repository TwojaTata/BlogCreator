package com.example.blogCreator.controllers;

import com.example.blogCreator.models.forms.CommentForm;
import com.example.blogCreator.models.forms.PostForm;
import com.example.blogCreator.models.services.BlogService;
import com.example.blogCreator.models.services.CommentService;
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
public class PostController {
    final PostService postService;
    final SessionService sessionService;
    final CommentService commentService;
    final BlogService blogService;


    @Autowired
    public PostController(PostService postService, SessionService sessionService, CommentService commentService, BlogService blogService) {
        this.postService = postService;
        this.sessionService = sessionService;
        this.commentService = commentService;
        this.blogService = blogService;
    }



    @GetMapping("/post")
    public String post(Model model) {
        if(!sessionService.isLoggedIn()){
            return "redirect:/login";
        }
        model.addAttribute("postForm", new PostForm());
        return "addPost";
    }

    @PostMapping("/post")
    public String post(@ModelAttribute("postForm") PostForm postForm){
        postService.addPost(postForm);
        return "redirect:/";
    }


    @GetMapping("/post/{id}")
    public String post(@PathVariable("id") int id,
                       Model model){
        model.addAttribute("blogData", blogService.getAllBlogData(postService.getAllPostData(id).getBlog().getId()));
        model.addAttribute("postData",  postService.getAllPostData(id));
        model.addAttribute("commentForm", new CommentForm());
        return "showPost";
    }

    @PostMapping("/comment/{id}")
    public String comment(@PathVariable("id") int id,
                          @ModelAttribute("commentForm") CommentForm comment){
        if(!sessionService.isLoggedIn()){
            return "redirect:/login";
        }
        commentService.addComment(comment, id);
        return "redirect:/post/" + id;
    }
}
