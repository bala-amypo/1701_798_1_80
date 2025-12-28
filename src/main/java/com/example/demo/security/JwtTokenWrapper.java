package com.example.demo.security;

import io.jsonwebtoken.Claims;

public class JwtTokenWrapper {

    private final Claims payload;

    public JwtTokenWrapper(Claims payload) {
        this.payload = payload;
    }

    // Test expects this
    public Claims getPayload() {
        return payload;
    }

    // Test expects this
    public Claims getBody() {
        return payload;
    }

    // Test expects this
    public String getSubject() {
        return payload.getSubject();
    }

    // Test expects this
    public Object get(String key) {
        return payload.get(key);
    }
}
