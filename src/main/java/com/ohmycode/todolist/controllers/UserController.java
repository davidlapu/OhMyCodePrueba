package com.ohmycode.todolist.controllers;

import com.ohmycode.todolist.models.entities.User;
import com.ohmycode.todolist.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/user")
    public List<User> getUsers() {
        return userService.findAll();
    }
}
