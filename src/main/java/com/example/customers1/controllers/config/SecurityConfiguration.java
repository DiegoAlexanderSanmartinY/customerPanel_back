package com.example.customers1.controllers.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private CustomAccessFilter customAccesFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        // httpSecurity.authorizeHttpRequests(request -> {
        //     request.requestMatchers("/api/register", "/api/auth/login").permitAll();
        //     request.requestMatchers("/api/**").authenticated();
        // });
        
        httpSecurity
        .addFilterBefore(customAccesFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();

    }

}
