package com.swapiffy.swapiffybe.service;

import com.swapiffy.swapiffybe.dao.userToken.IUserTokenDao;
import com.swapiffy.swapiffybe.dao.userToken.UserTokenImpl;
import com.swapiffy.swapiffybe.dto.LoginResponse;
import com.swapiffy.swapiffybe.entity.User;
import com.swapiffy.swapiffybe.entity.UserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


@Service
public class AuthService {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserService userService;
    @Autowired
    public AuthService(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;

        if (userService == null || tokenService == null) {
            throw new IllegalArgumentException("userService ve tokenService null olamaz");
        }

    }

    public ResponseEntity<LoginResponse> login(String email, String password) {
        String token = null;
        User authenticatedUser = userService.authenticateUser(email, password);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oneHourLater = now.plus(1, ChronoUnit.HOURS);

        if (authenticatedUser != null) {
            token = tokenService.generateToken(email);
            IUserTokenDao userTokenDao = new UserTokenImpl();
            UserToken userToken = new UserToken();
            userToken.setToken(token);
            userToken.setUser(authenticatedUser.getId());
            userToken.setExpirationTime(oneHourLater);
            userTokenDao.save(userToken);

            LoginResponse loginResponse = new LoginResponse(token, authenticatedUser);

            return ResponseEntity.ok(loginResponse);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
