package com.udacity.jwdnd.course1.cloudstorage.controller;

        import com.udacity.jwdnd.course1.cloudstorage.model.User;
        import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
        import org.springframework.stereotype.Component;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.ModelAttribute;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignUpController {
    private final UserService userService;

    public SignUpController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String signup() {
        System.out.println("In sign up get mapping");
        return "signup";
    }

    @PostMapping()
    public String doSignup(@ModelAttribute User user, Model model) {
        String errorMsg = null;
        // if the username already exists
        if(!userService.isUsernameAvailable(user.getUsername())) {
            System.out.println("username is not available!");
            errorMsg = "Username already in use";
        }
        //otherwise, we potentially have a valid case here
        else {
            userService.createUser(user);
        }
        // log success or error
        if (errorMsg == null) {
            model.addAttribute("signupSuccess", true);
        } else {
            model.addAttribute("signupError", errorMsg);
        }
        return "signup";
    }
}
