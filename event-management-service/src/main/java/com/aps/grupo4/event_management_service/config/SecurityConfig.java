package com.aps.grupo4.event_management_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("usuario1")
                .password(passwordEncoder().encode("teste123"))
                .roles("USER")
                .build();

        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder().encode("teste123"))
                .roles("ADMIN")
                .build();

        UserDetails gestorEventos = User.withUsername("gestor-eventos")
                .password(passwordEncoder().encode("teste123"))
                .roles("GESTOR")
                .build();

        return new InMemoryUserDetailsManager(user, admin, gestorEventos);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                        configurer
                                .requestMatchers( "/event-management/events/**").hasAnyRole("USER", "ADMIN", "GESTOR")
                                .requestMatchers("/event-management/**").hasAnyRole("ADMIN", "GESTOR")
                                .anyRequest()
                                .authenticated()
                );

        http.csrf(csrf -> csrf.disable());

        http.httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
