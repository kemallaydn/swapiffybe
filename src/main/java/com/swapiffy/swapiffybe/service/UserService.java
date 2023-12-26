package com.swapiffy.swapiffybe.service;

import com.swapiffy.swapiffybe.controller.UserController;
import com.swapiffy.swapiffybe.dao.user.IUserDao;
import com.swapiffy.swapiffybe.dao.user.UserDaoImpl;
import com.swapiffy.swapiffybe.dao.userToken.IUserTokenDao;
import com.swapiffy.swapiffybe.dao.userToken.UserTokenImpl;
import com.swapiffy.swapiffybe.entity.User;
import com.swapiffy.swapiffybe.entity.UserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    private  UserService userService;

    private  final TokenService tokenService = new TokenService();
    public User addUser(User user) {
        IUserDao userDao = new UserDaoImpl();
        return userDao.save(user);
    }

    public User authenticateUser(String email, String password) {
        IUserDao userDao = new UserDaoImpl();
        User user = userDao.getUserByEmail(email);

        if (user == null || !user.getPassword().equals(password)) {
            return null;
        }
        return user;
    }

    public User getUserById(long kullaniciId) {

        IUserDao userDao = new UserDaoImpl();
        User user = userDao.getUserById(kullaniciId);
        return user;
    }
}
