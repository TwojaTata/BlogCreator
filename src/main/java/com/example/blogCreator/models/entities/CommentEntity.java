package com.example.blogCreator.models.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "comment")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String context;


    @JoinColumn(name = "user_id")
    @ManyToOne
    private UserEntity user;

    @JoinColumn(name = "post_id")
    @ManyToOne
    private PostEntity post;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;
}
