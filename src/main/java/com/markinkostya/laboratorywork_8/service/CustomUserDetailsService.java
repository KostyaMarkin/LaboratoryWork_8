package com.markinkostya.laboratorywork_8.service;

import com.markinkostya.laboratorywork_8.entity.User;
import com.markinkostya.laboratorywork_8.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        log.info("Attempting to load user by email: {}", usernameOrEmail);

        User user = userRepository.findByEmail(usernameOrEmail);
        if(user != null) {
            log.info("User found: {} with roles: {}", user.getEmail(),
                    user.getRoles().stream().map(role -> role.getName()).collect(Collectors.toList()));

            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(), user.getPassword(),
                    user.getRoles().stream()
                            .map((role) -> new SimpleGrantedAuthority(role.getName()))
                            .collect(Collectors.toList()));

        } else {
            log.warn("User not found for email: {}", usernameOrEmail);
            throw new UsernameNotFoundException("Invalid email or password");
        }
    }
}
