package com.example.blogCreator.models.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String username;
    private String email;
    private String password;
    @Column(name = "creation_date")
    private LocalDateTime registerDate;

    @OneToOne(mappedBy = "user", fetch = FetchType.EAGER /* ,cascade = CascadeType.ALL */, orphanRemoval = true)
    private BlogEntity blog;
}