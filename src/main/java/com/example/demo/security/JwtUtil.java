package com.example.demo.security;

import com.example.demo.entity.UserAccount;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

public class JwtUtil {

    private String secretKey = "DefaultSecretKeyForDemoProject"; // can be customized
    private long validityInMilliseconds = 3600_000; // 1h

    private Key key;

    public void initKey() {
        byte[] decodedKey = Base64.getDecoder().decode(secretKey.getBytes());
        key = new SecretKeySpec(decodedKey, 0, decodedKey.length, SignatureAlgorithm.HS256.getJcaName());
    }

    // Generate token with claims and subject
    public String generateToken(Map<String, Object> claims, String subject) {
        long now = System.currentTimeMillis();
        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + validityInMilliseconds))
                .signWith(SignatureAlgorithm.HS256, key);
        return builder.compact();
    }

    // Generate token directly for a UserAccount
    public String generateTokenForUser(UserAccount ua) {
        Claims claims = Jwts.claims();
        claims.put("email", ua.getEmail());
        claims.put("role", ua.getRole());
        claims.put("userId", ua.getId());
        return generateToken(claims, ua.getEmail());
    }

    // Extract username (subject) from token
    public String extractUsername(String token) {
        return parseToken(token).getBody().getSubject();
    }

    // Extract role from token
    public String extractRole(String token) {
        return (String) parseToken(token).getBody().get("role");
    }

    // Extract userId from token
    public Long extractUserId(String token) {
        Object id = parseToken(token).getBody().get("userId");
        return id != null ? Long.valueOf(id.toString()) : null;
    }

    // Validate token
    public boolean isTokenValid(String token, String username) {
        try {
            String tokenUser = extractUsername(token);
            return tokenUser.equals(username) && !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

    // Check expiration
    private boolean isTokenExpired(String token) {
        Date expiration = parseToken(token).getBody().getExpiration();
        return expiration.before(new Date());
    }

    // Parse token and return Jws<Claims> for tests
    public Jws<Claims> parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token);
    }
}
