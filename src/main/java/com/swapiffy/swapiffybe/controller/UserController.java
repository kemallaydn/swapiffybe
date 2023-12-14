package com.swapiffy.swapiffybe.controller;

import com.swapiffy.swapiffybe.entity.User;
import com.swapiffy.swapiffybe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserService userService = new UserService();
    @PostMapping("/saveOrder")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

}
