package com.swapiffy.swapiffybe.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "\"user_token\"")
public class UserToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long user;

    @Column(name = "token")
    private String token;

    @Column(name = "expiration_time")
    private LocalDateTime expirationTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(LocalDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }

    public UserToken() {
        // Boş kurucu metot
    }

    public UserToken(Long id, String token, LocalDateTime expirationTime) {
        this.id = id;
        this.token = token;
        this.expirationTime = expirationTime;
    }

    // Getter ve Setter metotları
}
