package com.example.customers1.controllers.config;

import java.io.IOException;
import java.security.Security;
import java.util.Collections;
import java.util.Arrays;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.customers1.utils.JwtUtil;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAccessFilter implements Filter {

    public CustomAccessFilter() {}

    // @Override
    // public void init(FilterConfig filterConfig) throws ServletException {    
    // }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        boolean authorized = isAuthorized(request);
        if(authorized) {  
            SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(null, 
                                            null, Collections.emptyList()));
            filterChain.doFilter(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    // @Override
    // public void destroy() {}

    private boolean isAuthorized(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String currentUrl = request.getRequestURI();

        String [] availableUrl = new String [] {
            "/api/auth/register", "/api/auth/login"
        };

        boolean authorized = Arrays.stream(availableUrl)
                            .anyMatch(url -> currentUrl.equals(url));

        boolean isApiResource = currentUrl.startsWith("/api");

        if (authorized || !isApiResource ) return true;

        try {
            String userId = JwtUtil.getUserIdByToken(token);
            return true; 
        } catch (Exception e) {
            return false;
        } 

    }

    

}
