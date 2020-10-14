package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Session;
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

    private Session session;

    public LoginController(Session session) {
        this.session = session;
    }

    @GetMapping
    public String getLoginView(Model model){
        System.out.println("in get login view");
        String signupSuccess = session.getValue("signupSuccess");

        if(signupSuccess != null){
            model.addAttribute("signupSuccess", signupSuccess);
            System.out.println(signupSuccess);
        }
        session.setAttribute("signupSuccess", null);

        return "login";
    }
}
