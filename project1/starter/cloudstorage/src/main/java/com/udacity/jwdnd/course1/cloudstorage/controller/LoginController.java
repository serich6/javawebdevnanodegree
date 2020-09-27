package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping()
    public String login() {
        System.out.println("In login get mapping");
        return "login";
    }

    @PostMapping()
    public String doLogin() {
        System.out.println("In login post mapping");
        return "home";
    }
}
