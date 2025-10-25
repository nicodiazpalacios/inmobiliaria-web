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
                        // Permite acceso público a la raíz, recursos, LOGIN y REGISTRO
                        .requestMatchers("/", "/login", "/register", "/css/**", "/js/**", "/images/**").permitAll() // <--
                                                                                                                    // ¡Rutas
                                                                                                                    // /login
                                                                                                                    // y
                                                                                                                    // /register
                                                                                                                    // añadidas!
                        .anyRequest().authenticated())

                // Configuración de Login
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .permitAll())

        // Opcional: Deshabilita CSRF si lo necesitas para formularios simples o APIs,
        // pero si usas Thymeleaf, generalmente lo mantienes.
        // .csrf(csrf -> csrf.disable());

        ; // Asegúrate de terminar la cadena con un punto y coma

        return http.build();
    }
}