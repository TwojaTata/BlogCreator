package com.example.blogCreator.models.services;

import com.example.blogCreator.models.entities.PostEntity;
import com.example.blogCreator.models.forms.PostForm;
import com.example.blogCreator.models.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final SessionService sessionService;
    private final PostRepository postRepository;


    @Autowired
    public PostService(SessionService sessionService, PostRepository postRepository) {
        this.sessionService = sessionService;
        this.postRepository = postRepository;

    }

    public void addPost(PostForm postForm){
        PostEntity postEntity = createEntityFromForm(postForm);
        postRepository.save(postEntity);
    }

    private PostEntity createEntityFromForm(PostForm postForm) {
        PostEntity postEntity = new PostEntity();
        postEntity.setContext(postForm.getContext());
        postEntity.setTitle(postForm.getTitle());
        postEntity.setBlog(sessionService.getUserEntity().getBlog());

        return postEntity;
    }

    public Iterable<PostEntity> getAllPostsByBlogId(int id){
        return postRepository.findAllByBlog_Id(id);
    }

    public PostEntity getAllPostData(int id) {
        return postRepository.findById(id).get();
    }

}
