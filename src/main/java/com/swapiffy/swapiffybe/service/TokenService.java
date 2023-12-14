package com.swapiffy.swapiffybe.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {
    private static final String SECRET_KEY = "123456"; // Güvenli bir şekilde saklanmalıdır

    public String generateToken(String username) {
        Date expirationDate = new Date(System.currentTimeMillis() + 600000); // 10 dakika
        return Jwts.builder()
                .setSubject(username)
                .signWith(Keys.secretKeyFor(SignatureAlgorithm.HS512))
                .compact();
    }
}
