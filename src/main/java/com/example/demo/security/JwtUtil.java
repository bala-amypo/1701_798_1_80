package com.example.demo.security;

import com.example.demo.entity.UserAccount;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {

    // Use SAME secret as application.properties
    private static final String SECRET =
            "your-256-bit-secret-key-for-jwt-generation-must-be-at-least-32-characters-long";

    private final SecretKey key =
            Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    public String generateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateTokenForUser(UserAccount user) {
        Map<String, Object> claims = Map.of(
                "email", user.getEmail(),
                "role", user.getRole(),
                "userId", user.getId()
        );
        return generateToken(claims, user.getEmail());
    }

    public String extractUsername(String token) {
        return parseToken(token).getPayload().getSubject();
    }

    public String extractRole(String token) {
        return parseToken(token).getPayload().get("role", String.class);
    }

    public Long extractUserId(String token) {
        Object userId = parseToken(token).getPayload().get("userId");
        return userId instanceof Integer
                ? ((Integer) userId).longValue()
                : (Long) userId;
    }

    public boolean isTokenValid(String token, String username) {
        try {
            return extractUsername(token).equals(username)
                    && !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        return parseToken(token)
                .getPayload()
                .getExpiration()
                .before(new Date());
    }

    public Jws<Claims> parseToken(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token);
    }
}
