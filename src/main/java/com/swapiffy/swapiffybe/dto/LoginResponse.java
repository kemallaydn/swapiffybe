package com.swapiffy.swapiffybe.dto;

import com.swapiffy.swapiffybe.entity.User;

public class LoginResponse {
    private String token;
    private User authenticatedUser;

    // Constructors, getters, and setters

    // Constructor with both token and authenticatedUser
    public LoginResponse(String token, User authenticatedUser) {
        this.token = token;
        this.authenticatedUser = authenticatedUser;
    }

    // Getters and setters for token and authenticatedUser
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getAuthenticatedUser() {
        return authenticatedUser;
    }

    public void setAuthenticatedUser(User authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
    }
}