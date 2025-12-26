package com.example.demo.security;

import com.example.demo.entity.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import java.util.Collections;
import java.util.Optional;

public class CustomUserDetailsService implements UserDetailsService {

    private final UserAccountRepository userRepo;

    public CustomUserDetailsService(UserAccountRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserAccount> userOpt = userRepo.findByEmail(email);
        if (userOpt.isEmpty()) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        UserAccount ua = userOpt.get();
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + ua.getRole());
        return new org.springframework.security.core.userdetails.User(
                ua.getEmail(),
                ua.getPassword(),
                Collections.singleton(authority)
        );
    }
}
