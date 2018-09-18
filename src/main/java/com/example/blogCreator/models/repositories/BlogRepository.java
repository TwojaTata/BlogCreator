package com.example.blogCreator.models.repositories;

import com.example.blogCreator.models.entities.BlogEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends CrudRepository<BlogEntity, Integer>{
    List<BlogEntity> findAllByOrderByIdDesc();

}
