package com.users.users.controller;

import com.users.users.entity.User;
import com.users.users.entity.Vo;
import com.users.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/department/{id}")
    public User saveUser(@RequestBody User user, @PathVariable Long id){
        return userService.saveUser(user, id);
    }

    @GetMapping("/{id}")
    public Vo getUserById(@PathVariable Long id){
        return userService.findUserById(id);
    }
}
