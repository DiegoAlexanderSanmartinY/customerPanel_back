package com.example.customers1.services;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.customers1.entities.User;
import com.example.customers1.repository.UserRepository;
import com.google.common.hash.Hashing;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final String SECRET_KEY = "Diego";

    public User getUser(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent())
            return user.get();
        return null;
    }

    public List<User> getAllUsers() {

        List<User> list = new ArrayList<>();
        Iterable<User> users = userRepository.findAll();
        users.forEach(user -> list.add(user));
        return list;
    }

    public void removeUser(Integer id) {
        userRepository.deleteById(id);

    }

    public User addUser(User user) {
        String hashPassword = user.getPassword()+SECRET_KEY;

        String sha256hex = Hashing.sha256()
                            .hashString(hashPassword, StandardCharsets.UTF_8)
                            .toString();


        user.setPassword(sha256hex);
        userRepository.save(user);
        return user;
    }

    public void updateUser(Integer id, User user) {

        user.setId(id);
        userRepository.save(user);

    }

    public List<User> searchUser(String email,
            String address) {

        return userRepository.findByEmailOrAddress(email, address);

    }


}
