package com.example.customers1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.example.customers1.entities.User;
import com.example.customers1.services.UserService;
import com.example.customers1.utils.JwtUtil;



@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UserController {
    
    @Autowired
    private UserService service;


    @GetMapping("/user/{id}")
    public User getUser(@PathVariable Integer id) {
        return service.getUser(id);
    }

    @GetMapping("/user")
    public List<User> getAllUsers(String token) {

    //    String userId = JwtUtil.getUserIdByToken(token);

        return service.getAllUsers();
    }

    @DeleteMapping ("/user/{id}")
    public void removeUser(@PathVariable Integer id) {
        service.removeUser(id);

    }

    @PostMapping ("/user")
    public User addUser(@RequestBody User user) {
        return service.addUser(user);
    }

    @PutMapping ("/user/{id}")
    public void updateUser(@PathVariable Integer id,
                               @RequestBody User user) {
        service.updateUser(id, user);
    }

    @GetMapping("/user/search")
    public List<User> searchUser(@RequestParam(name = "email", required = false) String email,
                                         @RequestParam(name = "address", required = false) String address) {
        return service.searchUser(email, address);
    }
    

}
