package org.example.vexto.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/css/**", "/js/**").permitAll() // Lejohet për të gjithë [cite: 20]
                        .requestMatchers("/admin/**").hasRole("ADMIN") // Vetëm ADMIN [cite: 27]
                        .requestMatchers("/app/**").hasAnyRole("USER", "ADMIN") // USER & ADMIN [cite: 28]
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login") // Custom Login Page [cite: 20]
                        .defaultSuccessUrl("/app/products", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )
                .exceptionHandling(ex -> ex
                        .accessDeniedPage("/access-denied") // Custom Access Denied [cite: 29]
                );

        return http.build();
    }
}