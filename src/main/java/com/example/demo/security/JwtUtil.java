package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

public class JwtUtil {

    private SecretKey key;
    private long expiration;

    // ⭐ Constructor used by configuration
    public JwtUtil(String secret, Long expiration) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.expiration = (expiration != null) ? expiration : 3600000L;
    }

    // ⭐ Zero-arg constructor required by test file
    public JwtUtil() {
        this.key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        this.expiration = 3600000L;
    }

    // ⭐ Tests call jwtUtil.initKey()
    public void initKey() {
        this.key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    // Generate a token
    public String generateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .signWith(key)
                .compact();
    }

    // Generate token for a UserAccount entity
    public String generateTokenForUser(com.example.demo.entity.UserAccount user) {
        Map<String, Object> claims = Map.of(
                "userId", user.getId(),
                "email", user.getEmail(),
                "role", user.getRole()
        );
        return generateToken(claims, user.getEmail());
    }

    // ⭐ Parse token and wrap in ParsedToken so tests can call getPayload()
    public ParsedToken parseToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return new ParsedToken(claims);
    }

    // ==========================
    //   TEST COMPATIBILITY API
    // ==========================

    public ParsedToken getPayload(String token) {
        return parseToken(token);
    }

    public String extractUsername(String token) {
        return parseToken(token).getSubject();
    }

    public Long extractUserId(String token) {
        return Long.valueOf(parseToken(token).get("userId").toString());
    }

    public String extractRole(String token) {
        return parseToken(token).get("role").toString();
    }

    public boolean isTokenValid(String token, String username) {
        return extractUsername(token).equals(username);
    }

    // ==========================
    //   WRAPPER CLASS REQUIRED
    // ==========================

    public static class ParsedToken {
        private final Claims claims;

        public ParsedToken(Claims claims) {
            this.claims = claims;
        }

        // ⭐ Tests do: jwtUtil.parseToken(token).getPayload()
        public Claims getPayload() {
            return claims;
        }

        // ⭐ Tests do: ...getPayload().get("email")
        public Object get(String key) {
            return claims.get(key);
        }

        // ⭐ Tests call subject
        public String getSubject() {
            return claims.getSubject();
        }
    }
}
