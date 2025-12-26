package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

public class JwtUtil {

    // Secret key for signing the token (use a secure random key in production)
    private final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Token validity (e.g., 1 hour)
    private final long jwtExpirationMs = 3600000;

    // Generate a JWT token
    public String generateToken(String username, Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)        // Claims map
                .setSubject(username)      // Subject (username)
                .setIssuedAt(new Date())   // Current time
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs)) // Expiry
                .signWith(secretKey)       // Sign with secret key
                .compact();
    }

    // Extract claims from JWT token
    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)  // Set signing key
                .build()
                .parseClaimsJws(token)     // Parse the token
                .getBody();                // Get claims (body)
    }

    // Extract username (subject) from token
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    // Extract expiration date
    public Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }

    // Check if token is expired
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Validate token
    public boolean validateToken(String token, String username) {
        final String tokenUsername = extractUsername(token);
        return (tokenUsername.equals(username) && !isTokenExpired(token));
    }

    // Get the secret key (optional, if needed elsewhere)
    public SecretKey getSecretKey() {
        return secretKey;
    }
}
