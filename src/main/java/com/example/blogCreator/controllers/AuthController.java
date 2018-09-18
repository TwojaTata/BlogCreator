package com.example.blogCreator.controllers;

import com.example.blogCreator.models.forms.RegisterForm;
import com.example.blogCreator.models.services.AuthService;
import com.example.blogCreator.models.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    private final AuthService authService;
    private final SessionService sessionService;

    @Autowired
    public AuthController(AuthService authService, SessionService sessionService) {
        this.authService = authService;
        this.sessionService = sessionService;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        Model model){

        if(!authService.tryLogin(email, password)){
            model.addAttribute("wrongPassOrEmail","Email or password is wrong");
            return "login";
        }
        return "redirect:/";
    }
    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("registerForm", new RegisterForm());
        return "register";
    }
    @PostMapping("/register")
    public String register(@ModelAttribute("registerForm") RegisterForm registerForm,
                           Model model){
        if(!authService.tryToRegister(registerForm)){
            model.addAttribute("emailAlreadyTaken", "Email is already taken");
            return "register";
        }
        model.addAttribute("registerSuccessfull", "successfully SignedUp");
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(){
        sessionService.setLoggedIn(false);
        sessionService.setUserEntity(null);

        return "redirect:/login";
    }
}

