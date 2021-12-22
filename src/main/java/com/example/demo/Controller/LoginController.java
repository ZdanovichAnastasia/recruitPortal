package com.example.demo.Controller;

import com.example.demo.BusinessLogic.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.Valid;

@Controller
public class LoginController {
    @Autowired
    UserService userService;

    @GetMapping({"/logIn"})
    public String logIn(Model model) {
        return "logIn";
    }

    @GetMapping({"/try"})
    public String tryP(Model model) {
        return "try";
    }
}
