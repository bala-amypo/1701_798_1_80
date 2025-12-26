package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // FIX: Disable CSRF for API endpoints (fixes 403 error)
            .csrf(csrf -> csrf.disable())
            
            // Configure authorization
            .authorizeHttpRequests(auth -> auth
                // Allow auth endpoints without authentication
                .requestMatchers("/auth/**").permitAll()
                
                // Allow Swagger/OpenAPI docs
                .requestMatchers(
                    "/swagger-ui/**", 
                    "/v3/api-docs/**", 
                    "/swagger-resources/**",
                    "/webjars/**"
                ).permitAll()
                
                // Allow H2 console if using in-memory DB for tests
                .requestMatchers("/h2-console/**").permitAll()
                
                // All other requests require authentication
                .anyRequest().authenticated()
            )
            
            // Configure session management
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            );
        
        // Allow frames for H2 console (for testing)
        http.headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));
        
        return http.build();
    }
}