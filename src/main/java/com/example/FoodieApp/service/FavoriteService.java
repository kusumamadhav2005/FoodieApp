package com.example.FoodieApp.service;

import com.example.FoodieApp.domain.Favorite;
import com.example.FoodieApp.domain.User;

import java.util.List;

public interface FavoriteService {

    User addFavorite(String email, Favorite favorite);
    List<Favorite> getFavorites(String email);
    User updateFavorite(String email, Favorite favorite);
    User deleteFavorite(String email, int fid);
}
