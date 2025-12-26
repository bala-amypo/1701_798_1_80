// @Component
// public class JwtUtil {

//     private Key key;

//     public void initKey() {
//         key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//     }

//     public String generateTokenForUser(UserAccount u) {
//         return Jwts.builder()
//                 .claim("email", u.getEmail())
//                 .claim("role", u.getRole())
//                 .claim("userId", u.getId())
//                 .setSubject(u.getEmail())
//                 .signWith(key)
//                 .compact();
//     }

//     public String extractUsername(String token) {
//         return parseToken(token).getBody().getSubject();
//     }

//     public Jws<Claims> parseToken(String token) {
//         return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
//     }

//     public boolean isTokenValid(String token, String username) {
//         return extractUsername(token).equals(username);
//     }
// }
package com.example.demo.security;

import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    // empty util for now (compile-safe)
}

