package com.example.FoodieApp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
@Service
public class TokenGeneratorImpl implements TokenGenerator {

    private final Key key;

    public TokenGeneratorImpl(@Value("${jwt.secret}") String base64SecretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(base64SecretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }


    @Override
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }
}

