package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping()
    public String home() {
        System.out.println("In home get mapping");
        return "home";
    }

    @PostMapping()
    public String logout(Model model) {
        System.out.println("in logout method");
        ///loggedOutSuccess
        model.addAttribute("loggedOutSuccess", true);
        return "login";
    }
}
