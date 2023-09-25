package com.empik.restapp.controllers;

import com.empik.restapp.dto.User;
import com.empik.restapp.service.UsersService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersController {
    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/{login}")
    public User getUser(@PathVariable String login) {
        return usersService.getUser(login);
    }
}
