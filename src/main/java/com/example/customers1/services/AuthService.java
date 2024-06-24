package com.example.customers1.services;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.customers1.entities.User;
import com.example.customers1.repository.UserRepository;
import com.google.common.hash.Hashing;



@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    private static final String SECRET_KEY = "Diego";

    public User login(String email, String password) {
        String hashPassword = password + SECRET_KEY;

        String sha256hex = Hashing.sha256()
                            .hashString(hashPassword, StandardCharsets.UTF_8)
                            .toString();
        
        User result = userRepository.findByEmailAndPassword(email, sha256hex);

        // List<User> result = userRepository.findByEmailAndPassword(email, sha256hex);

        if (Objects.isNull(result)) return null;
        return result;
        // if (result.isEmpty()) return null;
        // return result.get(0);
    }

}
