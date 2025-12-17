package com.example.FoodieApp.controller;

import com.example.FoodieApp.domain.Favorite;
import com.example.FoodieApp.domain.User;
import com.example.FoodieApp.service.TokenGenerator;
import com.example.FoodieApp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService service;
    private final TokenGenerator tokenGenerator;

    public UserController(UserService service, TokenGenerator tokenGenerator) {
        this.service = service;
        this.tokenGenerator = tokenGenerator;
    }

    @PostMapping("api/user/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            User savedUser = service.register(user);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        User loggedIn = service.login(user.getEmail(), user.getPassword());

        if (loggedIn == null || loggedIn.getEmail() == null) {
            return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
        }

        String token = tokenGenerator.generateToken(loggedIn.getEmail());

        Map<String, String> response = new HashMap<>();
        response.put("email", loggedIn.getEmail());
        response.put("token", token);

        return ResponseEntity.ok(response);
    }



}
