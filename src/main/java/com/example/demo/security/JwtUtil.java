package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {
    
    @Value("${jwt.secret:your-256-bit-secret-key-for-jwt-generation-must-be-at-least-32-characters-long}")
    private String secret;
    
    @Value("${jwt.expiration:86400000}")
    private long expiration;
    
    private SecretKey key;
    
    public void initKey() {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }
    
    public String generateToken(String email, String role, String name) {
        initKey();
        
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        claims.put("name", name);
        
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
    
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    
    private Claims extractAllClaims(String token) {
        initKey();
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    
    public String extractRole(String token) {
        return extractClaim(token, claims -> claims.get("role", String.class));
    }
    
    public String extractName(String token) {
        return extractClaim(token, claims -> claims.get("name", String.class));
    }
    
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    
    public boolean validateToken(String token, String email) {
        final String username = extractUsername(token);
        return (username.equals(email) && !isTokenExpired(token));
    }
}