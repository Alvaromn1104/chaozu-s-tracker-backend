package com.chaozusTracker.controllers;

import com.chaozusTracker.dto.ApiDelivery;
import com.chaozusTracker.dto.LoginRequest;
import com.chaozusTracker.dto.LoginResponse;
import com.chaozusTracker.models.userRelated.Users;
import com.chaozusTracker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> users = this.userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/create")
    public ResponseEntity<Users> createUser(@RequestBody Users user) {
        Users createdUser = this.userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest credentials) {
        ApiDelivery<LoginResponse> response = this.userService.login(credentials.getEmail(), credentials.getPasword());
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id){
        this.userService.deleteUserById(id);
    }

}
