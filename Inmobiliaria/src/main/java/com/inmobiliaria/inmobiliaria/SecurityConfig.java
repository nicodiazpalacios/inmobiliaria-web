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
                        // Permitir acceso público a la raíz y recursos
                        .requestMatchers("/", "/css/**", "/js/**", "/images/**").permitAll()
                        .anyRequest().authenticated())
                // AÑADIR ESTA CONFIGURACIÓN DE LOGIN EXPLÍCITA:
                .formLogin((form) -> form
                        .loginPage("/login") // Si no tienes /login, puedes dejarlo por defecto, pero especificaremos la
                                             // página de éxito.
                        .defaultSuccessUrl("/", true) // <--- CRÍTICO: Redirige a la raíz después del login.
                        .permitAll());

        return http.build();
    }
}