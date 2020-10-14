package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    private FileService filesService;
    private NoteService notesService;
    private CredentialService credentialsService;

    private EncryptionService encryptionService;

    @GetMapping()
    public String home() {
        System.out.println("In home get mapping");
        return "home";
    }

    @PostMapping()
    public String logout(Model model) {
        System.out.println("in logout method");
        model.addAttribute("loggedOutSuccess", true);
        return "login";
    }
}
