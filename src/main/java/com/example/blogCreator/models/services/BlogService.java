package com.example.blogCreator.models.services;

import com.example.blogCreator.models.entities.BlogEntity;
import com.example.blogCreator.models.forms.BlogForm;
import com.example.blogCreator.models.repositories.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BlogService {

    private final BlogRepository blogRepository;
    private final SessionService sessionService;

    @Autowired
    public BlogService(BlogRepository blogRepository, SessionService sessionService) {
        this.blogRepository = blogRepository;
        this.sessionService = sessionService;
    }

    public void addBlog(BlogForm blogForm){

        BlogEntity blogEntity = createBlogEntityFromForm(blogForm);
        blogRepository.save(blogEntity);
    }

    private BlogEntity createBlogEntityFromForm(BlogForm blogForm) {
        BlogEntity blogEntity = new BlogEntity();

        blogEntity.setTitle(blogForm.getTitle());
        blogEntity.setUser(sessionService.getUserEntity());

        return blogEntity;
    }
    public Iterable<BlogEntity> getAllBlogs(){

        return blogRepository.findAllByOrderByIdDesc();
    }

    public BlogEntity getAllBlogData(int id) {
        return blogRepository.findById(id).get();
    }

}
