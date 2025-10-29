package com.inmobiliaria.inmobiliaria;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .authorizeHttpRequests((requests) -> requests
                                                // Aseguramos que la nueva ruta /staff/admin-dashboard esté protegida
                                                .requestMatchers(
                                                                "/", "/login", "/register", "/contactar-agente",
                                                                "/staff/login",
                                                                "/search", "/propiedad/**",
                                                                "/staff/dashboard",
                                                                "/css/**", "/js/**", "/images/**")
                                                .permitAll()

                                                // Cualquier otra solicitud (incluyendo /staff/dashboard y
                                                // /staff/admin-dashboard)
                                                // requiere autenticación
                                                .anyRequest().authenticated())

                                // Configuración de la autenticación de formularios
                                .formLogin((form) -> form
                                                .loginPage("/login")
                                                .defaultSuccessUrl("/", true)
                                                .permitAll());

                return http.build();
        }
}