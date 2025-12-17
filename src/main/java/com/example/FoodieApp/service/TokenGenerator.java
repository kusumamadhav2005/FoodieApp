package com.example.FoodieApp.service;

import com.example.FoodieApp.domain.User;

public interface TokenGenerator {
    String generateToken(String user);
}
