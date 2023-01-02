package com.java.onlinestore.controllers;

import com.java.onlinestore.model.User;
import com.java.onlinestore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUser(){
        return userService.getAllUser();
    }

    @PostMapping("/addCustomer")
    public User addCustomer(@RequestBody User user){
        return userService.addCustomer(user);
    }

    @PostMapping("/addSeller")
    public User addSeller(@RequestBody User user){
        return userService.addSeller(user);
    }

    @PostMapping("/addOwner")
    public User addOwner(@RequestBody User user){
        return userService.addOwner(user);
    }
}
