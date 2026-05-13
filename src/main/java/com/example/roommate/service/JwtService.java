package com.example.roommate.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    private final SecretKey chiave = Keys.hmacShaKeyFor(
            "questaEUnaChiaveSegretaMoltoLungaPerJWT123!".getBytes()
    );

    public String generaToken(String email) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(chiave)
                .compact();
    }

    public String estraiEmail(String token) {
        return Jwts.parser()
                .verifyWith(chiave)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}