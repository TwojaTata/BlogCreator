package com.example.blogCreator.models.forms;

import lombok.Data;

@Data
public class RegisterForm {
    private String email;
    private String password;
    private String passwordAgain;
    private String username;
}
