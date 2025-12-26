package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            // VERY IMPORTANT: Disable CSRF
            .csrf(csrf -> csrf.disable())

            // Disable default login & basic auth
            .formLogin(form -> form.disable())
            .httpBasic(basic -> basic.disable())

            // Allow APIs and Swagger
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/",
                    "/api/**",
                    "/swagger-ui/**",
                    "/v3/api-docs/**"
                ).permitAll()
                .anyRequest().authenticated()
            );

        return http.build();
    }
}
