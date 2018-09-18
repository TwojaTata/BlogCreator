package com.example.blogCreator.models.repositories;

import com.example.blogCreator.models.entities.CommentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CommentRepository extends CrudRepository<CommentEntity, Integer> {

}
