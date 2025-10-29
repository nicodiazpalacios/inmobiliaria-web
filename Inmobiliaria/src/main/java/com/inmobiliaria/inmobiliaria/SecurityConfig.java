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
                                                // RUTAS PÚBLICAS: Todas las URLs de formularios y recursos estáticos
                                                .requestMatchers(
                                                                // Rutas de formularios
                                                                "/", "/login", "/register", "/contactar-agente",
                                                                "/staff/login", "/search", "/propiedad/**", // /propiedad/**
                                                                                                            // para ver
                                                                                                            // detalles
                                                                                                            // por ID
                                                                // Recursos estáticos
                                                                "/css/**", "/js/**", "/images/**")
                                                .permitAll()

                                                // Cualquier otra solicitud requiere autenticación
                                                .anyRequest().authenticated())

                                // Configuración de la autenticación de formularios
                                .formLogin((form) -> form
                                                .loginPage("/login")
                                                .defaultSuccessUrl("/", true)
                                                .permitAll());

                return http.build();
        }
}