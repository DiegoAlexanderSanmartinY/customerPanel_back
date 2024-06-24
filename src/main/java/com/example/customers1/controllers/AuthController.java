package com.example.customers1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.customers1.dto.RequestLogin;
import com.example.customers1.entities.User;
import com.example.customers1.services.AuthService;
import com.example.customers1.utils.JwtUtil;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public String login(@RequestBody RequestLogin requestLogin) {
        User user = authService.login ( requestLogin.getEmail(), 
                                      requestLogin.getPassword() );

        String token = JwtUtil.generateToken(user);
        return token;
    }
}
