package com.example.blogCreator.models.services;

import com.example.blogCreator.models.entities.CommentEntity;
import com.example.blogCreator.models.entities.PostEntity;
import com.example.blogCreator.models.entities.UserEntity;
import com.example.blogCreator.models.forms.CommentForm;
import com.example.blogCreator.models.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final SessionService sessionService;

    @Autowired
    public CommentService(CommentRepository commentRepository, SessionService sessionService) {
        this.commentRepository = commentRepository;
        this.sessionService = sessionService;
    }

    public void addComment(CommentForm commentForm, int postId){
        CommentEntity commentEntity = createCommentEntity(commentForm, postId);
        commentRepository.save(commentEntity);
    }

    private CommentEntity createCommentEntity(CommentForm commentForm, int postId) {
        CommentEntity commentEntity = new CommentEntity();
        PostEntity postEntity = new PostEntity();
        UserEntity userEntity = new UserEntity();

        postEntity.setId(postId);
        userEntity.setId(sessionService.getUserEntity().getId());

        commentEntity.setContext(commentForm.getContext());
        commentEntity.setPost(postEntity);
        commentEntity.setUser(userEntity);
        return commentEntity;
    }
}
