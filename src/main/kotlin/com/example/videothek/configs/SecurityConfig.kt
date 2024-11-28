package com.example.videothek.configs

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {

    @Bean
    public fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http
//            .sessionManagement(Customizer.withDefaults())
            .cors{ c -> c.disable() }
            .csrf{ c -> c.disable() }
            .authorizeHttpRequests({ auth ->
                auth
                    // api endpoints
                    .requestMatchers(
                        "/api/all",
                        "/api/get"
                    ).permitAll()

                    // static assets
                    .requestMatchers(
                        "*.css",
                        "*.png"
                    ).permitAll()

                    // ui pages
                    .requestMatchers(
                        "/",
                        "/search",
                        "/movie/*",
                        "/allMovies",
                        "/lend/*",
                        "/return/*"
                    ).permitAll()

                    .requestMatchers("/error").permitAll()
                    .anyRequest().authenticated()
            })
            .formLogin(Customizer.withDefaults())
            .httpBasic(Customizer.withDefaults())
            .logout({ logout -> logout.permitAll() })
            .build();
    }
}