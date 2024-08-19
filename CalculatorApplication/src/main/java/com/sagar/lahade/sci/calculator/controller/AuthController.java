package com.sagar.lahade.sci.calculator.controller;

import com.sagar.lahade.sci.calculator.model.UserInfo;
import com.sagar.lahade.sci.calculator.service.UserService;
import jakarta.servlet.http.HttpSession; // Updated import
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
    
    @GetMapping("/")
    public String landed() {
        return "login";
    }


    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session) {
        UserInfo user = userService.authenticateUser(username, password);
        if (user != null) {
            session.setAttribute("user", user);
            return "redirect:/calculator";
        }
        return "redirect:/login?error";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password) {
        userService.registerUser(username, password);
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
