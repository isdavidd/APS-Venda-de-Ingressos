package com.aps.grupo4.event_management_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        // Define os usuários em memória
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        manager.createUser(User.withUsername("admin")
                .password("{noop}123")
                .roles("ADMIN")
                .build());

        manager.createUser(User.withUsername("user")
                .password("{noop}123")
                .roles("USER")
                .build());

        return manager;
    }
}
