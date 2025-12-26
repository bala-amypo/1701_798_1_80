// package com.example.demo.security;

// import com.example.demo.entity.UserAccount;
// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jws;
// import io.jsonwebtoken.JwtException;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.io.Decoders;
// import io.jsonwebtoken.security.Keys;
// import jakarta.annotation.PostConstruct;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Component;

// import java.security.Key;
// import java.util.Date;
// import java.util.HashMap;
// import java.util.Map;

// @Component
// public class JwtUtil {
    
//     @Value("${jwt.secret:your-256-bit-secret-key-for-jwt-generation-must-be-at-least-32-characters-long}")
//     private String secret;
    
//     @Value("${jwt.expiration:86400000}")
//     private long expiration;
    
//     private Key key;
    
//     @PostConstruct
//     public void initKey() {
//         // Ensure we have a valid 256-bit key (32 characters)
//         String secretKey = secret;
//         if (secretKey.length() < 32) {
//             // Pad with zeros to reach 32 characters
//             StringBuilder padded = new StringBuilder(secretKey);
//             while (padded.length() < 32) {
//                 padded.append("0");
//             }
//             secretKey = padded.toString();
//         } else if (secretKey.length() > 32) {
//             // Truncate to 32 characters
//             secretKey = secretKey.substring(0, 32);
//         }
        
//         byte[] keyBytes = secretKey.getBytes();
//         this.key = Keys.hmacShaKeyFor(keyBytes);
//     }
    
//     public String generateToken(Map<String, Object> claims, String subject) {
//         return Jwts.builder()
//                 .setClaims(claims)
//                 .setSubject(subject)
//                 .setIssuedAt(new Date())
//                 .setExpiration(new Date(System.currentTimeMillis() + expiration))
//                 .signWith(key)
//                 .compact();
//     }
    
//     public String generateTokenForUser(UserAccount user) {
//         Map<String, Object> claims = new HashMap<>();
//         claims.put("userId", user.getId());
//         claims.put("email", user.getEmail());
//         claims.put("role", user.getRole());
//         claims.put("fullName", user.getFullName());
//         claims.put("department", user.getDepartment());
        
//         return generateToken(claims, user.getEmail());
//     }
    
//     public Jws<Claims> parseToken(String token) {
//         try {
//             return Jwts.parserBuilder()
//                     .setSigningKey(key)
//                     .build()
//                     .parseClaimsJws(token);
//         } catch (JwtException e) {
//             throw new RuntimeException("Invalid JWT token: " + e.getMessage(), e);
//         }
//     }
    
//     public String extractUsername(String token) {
//         return parseToken(token).getBody().getSubject();
//     }
    
//     public Long extractUserId(String token) {
//         return parseToken(token).getBody().get("userId", Long.class);
//     }
    
//     public String extractRole(String token) {
//         return parseToken(token).getBody().get("role", String.class);
//     }
    
//     public boolean isTokenValid(String token, String username) {
//         try {
//             String extractedUsername = extractUsername(token);
//             return extractedUsername.equals(username) && !isTokenExpired(token);
//         } catch (Exception e) {
//             return false;
//         }
//     }
    
//     private boolean isTokenExpired(String token) {
//         return parseToken(token).getBody().getExpiration().before(new Date());
//     }
// }

package com.example.demo.security;

import com.example.demo.entity.UserAccount;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
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
    
    private Key signingKey;
    
    @javax.annotation.PostConstruct
    public void initKey() {
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(
                DatatypeConverter.printBase64Binary(secret.getBytes()));
        signingKey = new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());
    }
    
    public String generateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS256, signingKey)
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
    
    public Jws<Claims> parseToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(signingKey)
                    .parseClaimsJws(token);
        } catch (JwtException e) {
            throw new RuntimeException("Invalid JWT token: " + e.getMessage(), e);
        }
    }
    
    public String extractUsername(String token) {
        return parseToken(token).getBody().getSubject();
    }
    
    public Long extractUserId(String token) {
        return parseToken(token).getBody().get("userId", Long.class);
    }
    
    public String extractRole(String token) {
        return parseToken(token).getBody().get("role", String.class);
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
        return parseToken(token).getBody().getExpiration().before(new Date());
    }
}