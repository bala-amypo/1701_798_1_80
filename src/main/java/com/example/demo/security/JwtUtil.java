package com.example.demo.security;

import com.example.demo.entity.UserAccount;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    
    @Value("${jwt.secret:sdjhgbwubwwbgwiub8QFQ8qg87G1bfewifbiuwg7iu8wefqhjk}")
    private String secret;
    
    @Value("${jwt.expiration:86400000}")
    private long expiration;
    
    private Key key;
    private JwtParser parser;
    
    @PostConstruct
    public void initKey() {
        // FIX: Check if secret is null and use a default
        if (this.secret == null) {
            this.secret = "sdjhgbwubwwbgwiub8QFQ8qg87G1bfewifbiuwg7iu8wefqhjk";
        }
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.parser = Jwts.parserBuilder().setSigningKey(key).build();
    }
    
    public String generateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key)
                .compact();
    }
    
    public String generateTokenForUser(UserAccount user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put("email", user.getEmail());
        claims.put("role", user.getRole());
        claims.put("fullName", user.getFullName());
        claims.put("department", user.getDepartment());
        
        return generateToken(claims, user.getEmail());
    }
    
    public JwtWrapper parseToken(String token) {
        try {
            Jws<Claims> jws = parser.parseClaimsJws(token);
            return new JwtWrapper(jws);
        } catch (JwtException e) {
            throw new RuntimeException("Invalid JWT token: " + e.getMessage(), e);
        }
    }
    
    public static class JwtWrapper {
        private final Jws<Claims> jws;
        
        public JwtWrapper(Jws<Claims> jws) {
            this.jws = jws;
        }
        
        public Claims getPayload() {
            return jws.getBody();
        }
        
        public Jws<Claims> getJws() {
            return jws;
        }
    }
    
    public String extractUsername(String token) {
        return parseToken(token).getPayload().getSubject();
    }
    
    public Long extractUserId(String token) {
        return parseToken(token).getPayload().get("userId", Long.class);
    }
    
    public String extractRole(String token) {
        return parseToken(token).getPayload().get("role", String.class);
    }
    
    public boolean isTokenValid(String token, String username) {
        try {
            String extractedUsername = extractUsername(token);
            return extractedUsername.equals(username) && !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }
    
    private boolean isTokenExpired(String token) {
        return parseToken(token).getPayload().getExpiration().before(new Date());
    }
}