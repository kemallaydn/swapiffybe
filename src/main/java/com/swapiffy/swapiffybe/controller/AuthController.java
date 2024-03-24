package com.swapiffy.swapiffybe.controller;
import com.swapiffy.swapiffybe.dao.deneme.DenemeDaoImpl;
import com.swapiffy.swapiffybe.dto.*;
import com.swapiffy.swapiffybe.entity.User;
import com.swapiffy.swapiffybe.entity.deneme;
import com.swapiffy.swapiffybe.service.AuthService;
import io.micrometer.core.ipc.http.HttpSender;
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
    @PostMapping("/deneme")
    public ResponseEntity<Object> deneme() {
        DenemeDaoImpl a = new DenemeDaoImpl();
        a.save(new deneme());

        return ResponseEntity.ok(HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody User userRegisterRequest) {
        try {
            authService.register(userRegisterRequest);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}

