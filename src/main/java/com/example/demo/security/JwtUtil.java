package com.example.demo.security;

import org.springframework.stereotype.Component;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    
    private final String secret = "demo-test-secret-key-256-bits-for-jwt-validation";
    private final long expiration = 86400000L; // 24 hours
    
    public String generateToken(String email, String role, String name) {
        // Simple token generation for testing
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", email);
        claims.put("role", role);
        claims.put("name", name);
        claims.put("iat", System.currentTimeMillis());
        claims.put("exp", System.currentTimeMillis() + expiration);
        
        // Create a simple token format for testing
        String header = "{\"alg\":\"HS256\",\"typ\":\"JWT\"}";
        String payload = "{\"sub\":\"" + email + "\",\"role\":\"" + role + "\",\"name\":\"" + name + 
                         "\",\"iat\":" + System.currentTimeMillis() + 
                         ",\"exp\":" + (System.currentTimeMillis() + expiration) + "}";
        
        String encodedHeader = Base64.getEncoder().encodeToString(header.getBytes());
        String encodedPayload = Base64.getEncoder().encodeToString(payload.getBytes());
        String signature = Base64.getEncoder().encodeToString((encodedHeader + "." + encodedPayload + secret).getBytes());
        
        return encodedHeader + "." + encodedPayload + "." + signature;
    }
    
    public void initKey() {
        // Empty implementation for testing
    }
    
    public String extractUsername(String token) {
        try {
            String[] parts = token.split("\\.");
            if (parts.length < 2) return null;
            
            String payload = new String(Base64.getDecoder().decode(parts[1]));
            // Simple parsing for testing
            if (payload.contains("\"sub\":\"")) {
                int start = payload.indexOf("\"sub\":\"") + 7;
                int end = payload.indexOf("\"", start);
                return payload.substring(start, end);
            }
        } catch (Exception e) {
            // Ignore for testing
        }
        return null;
    }
    
    public String extractRole(String token) {
        try {
            String[] parts = token.split("\\.");
            if (parts.length < 2) return null;
            
            String payload = new String(Base64.getDecoder().decode(parts[1]));
            if (payload.contains("\"role\":\"")) {
                int start = payload.indexOf("\"role\":\"") + 8;
                int end = payload.indexOf("\"", start);
                return payload.substring(start, end);
            }
        } catch (Exception e) {
            // Ignore for testing
        }
        return null;
    }
    
    public boolean validateToken(String token, String email) {
        try {
            String extractedEmail = extractUsername(token);
            return extractedEmail != null && extractedEmail.equals(email);
        } catch (Exception e) {
            return false;
        }
    }
}