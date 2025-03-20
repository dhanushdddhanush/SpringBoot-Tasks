package com.questk2.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.questk2.entity.User;
import com.questk2.repository.UserRepository;

@RestController
public class UserController {
    @Autowired
    UserRepository repo;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    @GetMapping("/users/username/{username}")
    public Optional<User> getUserByUsername(@PathVariable String username) {
        return repo.findByUsername(username);
    }

    @GetMapping("/users/email/{email}")
    public Optional<User> getUserByEmail(@PathVariable String email) {
        return repo.findByEmail(email);
    }

    @PostMapping("/user/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createUser(@RequestBody User user) {
        repo.save(user);
    }

    @PutMapping("/user/update/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        User user = repo.findById(id).orElse(null);
        if (user != null) {
            user.setUsername(userDetails.getUsername());
            user.setEmail(userDetails.getEmail());
            repo.save(user);
        }
        return user;
    }

    @DeleteMapping("/user/delete/{id}")
    public void removeUser(@PathVariable Long id) {
        repo.deleteById(id);
    }
}