package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping()
    public String loginView(UserService userService, Model model) {
        System.out.println("In login get mapping");
        System.out.println(model.getAttribute("invalidCredentialsError"));
        return "login";
    }

    @PostMapping()
    public String loginPost() {
        System.out.println("In login POST mapping");
        return "home";
    }

}
