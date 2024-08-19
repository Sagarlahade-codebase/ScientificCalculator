package com.sagar.lahade.sci.calculator.controller;

import com.sagar.lahade.sci.calculator.model.UserInfo;
import com.sagar.lahade.sci.calculator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public UserInfo registerUser(@RequestParam String username, @RequestParam String password) {
        return userService.registerUser(username, password);
    }

    @PostMapping("/login")
    public UserInfo loginUser(@RequestParam String username, @RequestParam String password) {
        return userService.authenticateUser(username, password);
    }
}
