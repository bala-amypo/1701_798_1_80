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

package com.example.demo.security;  // MUST be com.example.demo.security

import com.example.demo.entity.UserAccount;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    
    @Value("${jwt.secret:sdjhgbwubwwbgwiub8QFQ8qg87G1bfewifbiuwg7iu8wefqhjk}")
    private String secret;
    
    @Value("${jwt.expiration:86400000}")
    private long expiration;
    
    private SecretKey key;
    
    @PostConstruct
    public void initKey() {
        // Ensure the key is at least 256 bits (32 characters)
        String keyString = secret;
        if (keyString.length() < 32) {
            // Pad to reach 32 characters
            StringBuilder sb = new StringBuilder(keyString);
            while (sb.length() < 32) {
                sb.append("0");
            }
            keyString = sb.toString();
        } else if (keyString.length() > 32) {
            // Truncate if too long
            keyString = keyString.substring(0, 32);
        }
        this.key = Keys.hmacShaKeyFor(keyString.getBytes());
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
    
    public String generateToken(String email, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
    
    public Jws<Claims> parseToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
        } catch (JwtException e) {
            throw new RuntimeException("Invalid JWT token: " + e.getMessage(), e);
        }
    }
    
    public String extractUsername(String token) {
        return parseToken(token).getBody().getSubject();
    }
    
    public String extractEmail(String token) {
        return extractUsername(token);
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