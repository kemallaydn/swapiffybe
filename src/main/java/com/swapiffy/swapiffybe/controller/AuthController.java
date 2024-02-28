package com.swapiffy.swapiffybe.controller;
import com.swapiffy.swapiffybe.dto.*;
import com.swapiffy.swapiffybe.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody UserLoginRequest userLoginRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, Object> errorResponse = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errorResponse.put(error.getField(), error.getDefaultMessage())
            );
            return ResponseEntity.badRequest().body(errorResponse);
        }
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        BeanUtils.copyProperties(userLoginRequest, userLoginDTO);
       return authService.login(userLoginDTO);
    }
    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody UserRegisterRequest userRegisterRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, Object> errorResponse = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errorResponse.put(error.getField(), error.getDefaultMessage())
            );
            return ResponseEntity.badRequest().body(errorResponse);
        }
        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO();
        BeanUtils.copyProperties(userRegisterRequest, userRegistrationDTO);
        try {
            authService.register(userRegistrationDTO);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

}

