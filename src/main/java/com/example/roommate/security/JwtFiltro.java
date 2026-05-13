package com.example.roommate.security;

import com.example.roommate.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;

@Component
public class JwtFiltro extends OncePerRequestFilter {

    private final SecretKey chiave = Keys.hmacShaKeyFor(
            "questaEUnaChiaveSegretaMoltoLungaPerJWT123!".getBytes()
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        System.out.println("FILTRO CHIAMATO: " + request.getRequestURI());
        String path = request.getRequestURI();

        // queste rotte sono pubbliche, non richiedono token
        if (path.equals("/utenti/login") ||
                (path.equals("/utenti") && request.getMethod().equals("POST"))) {
            filterChain.doFilter(request, response);
            return;
        }

        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        try {
            System.out.println("TOKEN: " + chiave);
            System.out.println("PATH: " + path);
            String token = header.substring(7);
            Jwts.parser().verifyWith(chiave).build().parseSignedClaims(token);
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}