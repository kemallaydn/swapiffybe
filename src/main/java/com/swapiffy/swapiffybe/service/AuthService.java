package com.swapiffy.swapiffybe.service;

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
    private final TokenService tokenService;
    private final UserService userService;
    private final UserConverter userConverter;
    @Autowired
    public AuthService(ICartDao cartDao, UserService userService, TokenService tokenService, UserConverter userConverter) {
        this.cartDao = cartDao;
        this.userService = userService;
        this.tokenService = tokenService;
        this.userConverter = userConverter;

        if (userService == null || tokenService == null) {
            throw new IllegalArgumentException("userService ve tokenService null olamaz");
        }

    }
    public ResponseEntity<LoginResponse> register(UserRegistrationDTO loginForm){
        IUserDao userDao=new UserDaoImpl();
      User user=  userDao.save(userConverter.convertToEntity(loginForm));
        Card userCard = cartDao.getCard(user.getId());
        if (userCard==null){
           Card newCard=  new Card();
            newCard.setKullanici(user);
            newCard.setSepetUrunList(new ArrayList<>());
            cartDao.addCard(newCard);
        }
        return null;
    }
    public ResponseEntity<Object> login(UserLoginDTO userLoginDTO) {
        String token = null;
        User authenticatedUser = userService.authenticateUser(userLoginDTO.getEmail(), userLoginDTO.getPassword());
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oneHourLater = now.plus(1, ChronoUnit.HOURS);

        if (authenticatedUser != null) {
            token = tokenService.generateToken(userLoginDTO.getEmail());
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
