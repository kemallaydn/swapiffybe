package com.swapiffy.swapiffybe.controller;
import com.swapiffy.swapiffybe.dto.LoginForm;
import com.swapiffy.swapiffybe.dto.LoginResponse;
import com.swapiffy.swapiffybe.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginForm loginForm) {
       return authService.login(loginForm.getEmail(),loginForm.getPassword());
    }
}

