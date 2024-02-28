package com.swapiffy.swapiffybe.controller;

import com.swapiffy.swapiffybe.dao.BaseDao;
import com.swapiffy.swapiffybe.dao.IBaseDao;
import com.swapiffy.swapiffybe.entity.User;
import com.swapiffy.swapiffybe.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.PanelUI;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("users")
public class UserController  {
    private final UserService userService = new UserService();
    @PostMapping("/saveOrder")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }



}
