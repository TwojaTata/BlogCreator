package com.example.blogCreator.models.repositories;

import com.example.blogCreator.models.entities.PostEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<PostEntity, Integer> {
    List<PostEntity> findAllByOrderByIdDesc();
    List<PostEntity> findAllByBlog_Id(int id);
}
