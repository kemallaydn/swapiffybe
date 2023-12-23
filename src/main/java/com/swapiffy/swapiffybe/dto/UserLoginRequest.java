package com.swapiffy.swapiffybe.dto;

import jakarta.validation.constraints.NotBlank;


public class UserLoginRequest {

    @NotBlank(message = "Email cannot be blank")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
// Getter ve Setter metotları

    // İsteğe bağlı olarak, diğer validasyon kuralları ekleyebilirsiniz
}
