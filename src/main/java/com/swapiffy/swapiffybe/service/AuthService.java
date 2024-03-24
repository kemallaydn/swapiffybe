package com.swapiffy.swapiffybe.service;

import com.swapiffy.swapiffybe.ServiceFacade;
import com.swapiffy.swapiffybe.dao.cart.ICartDao;
import com.swapiffy.swapiffybe.dao.user.IUserDao;
import com.swapiffy.swapiffybe.dao.user.UserDaoImpl;
import com.swapiffy.swapiffybe.dao.userToken.IUserTokenDao;
import com.swapiffy.swapiffybe.dao.userToken.UserTokenImpl;
import com.swapiffy.swapiffybe.dto.*;
import com.swapiffy.swapiffybe.dto.converter.UserConverter;
import com.swapiffy.swapiffybe.entity.Card;
import com.swapiffy.swapiffybe.entity.User;
import com.swapiffy.swapiffybe.entity.UserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;


@Service
public class AuthService {
    private final ICartDao cartDao;
    private  IUserDao userDao=null;
    private final TokenService tokenService;
    private final UserService userService;
    private final UserConverter userConverter;

    @Autowired
    public AuthService(ICartDao cartDao, UserService userService, TokenService tokenService, UserConverter userConverter) {
        userDao = ServiceFacade.getInstance().getUserDao();
        this.cartDao = cartDao;
        this.userService = userService;
        this.tokenService = tokenService;
        this.userConverter = userConverter;
        userDao = ServiceFacade.getInstance().getUserDao();

        if (userService == null || tokenService == null) {
            throw new IllegalArgumentException("userService ve tokenService null olamaz");
        }

    }
    public void register(User user) {
        LocalDateTime now = LocalDateTime.now();
        user.setRegistrationDate(now.toString());
        user.setAccountStatus("ACTIVE");
        userDao.save(user);
    }
    public ResponseEntity<Object> login(UserLoginDTO userLoginDTO) {
        String token = null;
        User authenticatedUser = userDao.getUserByEmail(userLoginDTO.getEmail());

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oneHourLater = now.plus(1, ChronoUnit.HOURS);

        if (authenticatedUser != null) {
            token = tokenService.generateToken(userLoginDTO.getEmail());
            IUserTokenDao userTokenDao = new UserTokenImpl();
            UserToken userToken = new UserToken();
            userToken.setTokenValue(token);
            userToken.setUserId(authenticatedUser.getId());
            userToken.setLastUsageDate(oneHourLater);
            userToken.setCreationDate(now);
            UserLoginResponse loginResponse = new UserLoginResponse();
            loginResponse.setToken(token);
            loginResponse.setSurname(authenticatedUser.getLastName());
            loginResponse.setName(authenticatedUser.getFirstName());
            loginResponse.setEmail(authenticatedUser.getEmail());
            loginResponse.setId(authenticatedUser.getId());
            loginResponse.setToken(token);
            return ResponseEntity.ok(loginResponse);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
