package com.example.FoodieApp.service;

import com.example.FoodieApp.domain.Favorite;
import com.example.FoodieApp.domain.User;

import java.util.List;

public interface UserService {

    User register(User user);
    User login(String email, String password);
    User addFavorite(String email, Favorite favorite);
    List<Favorite> getFavorites(String email);
}
