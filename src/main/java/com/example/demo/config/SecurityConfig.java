package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    private final JwtAuthFilter jwtAuthFilter;
    
    public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // FIX 1: Disable CSRF for API endpoints (fixes 403 error)
            .csrf(csrf -> csrf.disable())
            
            // FIX 2: Configure CORS
            .cors(cors -> cors.configure(http))
            
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
            )
            
            // Add JWT filter
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        
        // Allow frames for H2 console (for testing)
        http.headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));
        
        return http.build();
    }
}