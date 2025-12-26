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
    
    @Value("${jwt.secret:your-256-bit-secret-key-for-jwt-generation-must-be-at-least-32-characters-long}")
    private String secret;
    
    @Value("${jwt.expiration:86400000}")
    private long expiration;
    
    private Key key;
    
    @PostConstruct
    public void initKey() {
        // Ensure the key is at least 256 bits (32 characters)
        byte[] keyBytes;
        if (secret.length() < 32) {
            // Pad the secret if it's too short
            keyBytes = new byte[32];
            byte[] secretBytes = secret.getBytes();
            System.arraycopy(secretBytes, 0, keyBytes, 0, Math.min(secretBytes.length, 32));
        } else {
            keyBytes = secret.substring(0, 32).getBytes();
        }
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }
    
    public String generateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key, SignatureAlgorithm.HS256)
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
    
    public Claims parseToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            throw new RuntimeException("Invalid JWT token", e);
        }
    }
    
    public String extractUsername(String token) {
        return parseToken(token).getSubject();
    }
    
    public Long extractUserId(String token) {
        return parseToken(token).get("userId", Long.class);
    }
    
    public String extractRole(String token) {
        return parseToken(token).get("role", String.class);
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
        return parseToken(token).getExpiration().before(new Date());
    }
}