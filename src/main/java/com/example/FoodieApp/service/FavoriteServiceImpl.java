package com.example.FoodieApp.service;

import com.example.FoodieApp.domain.Favorite;
import com.example.FoodieApp.domain.User;
import com.example.FoodieApp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    private final UserRepository userRepository;

    public FavoriteServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User addFavorite(String email, Favorite favorite) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        List<Favorite> favorites = user.getFavoriteList();
        if (favorites == null) {
            favorites = new ArrayList<>();
        }

        favorites.add(favorite);
        user.setFavoriteList(favorites);

        return userRepository.save(user);
    }

    @Override
    public List<Favorite> getFavorites(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return user.getFavoriteList();
    }

    @Override
    public User updateFavorite(String email, Favorite favorite) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        List<Favorite> favorites = user.getFavoriteList();
        if (favorites == null || favorites.isEmpty()) {
            throw new RuntimeException("No favorites to update");
        }

        // Update by restaurantId
        for (Favorite f : favorites) {
            if (f.getRestaurantId().equals(favorite.getRestaurantId())) {
                f.setRestaurantName(favorite.getRestaurantName());
                f.setCuisine(favorite.getCuisine());
                return userRepository.save(user);
            }
        }

        throw new RuntimeException("Favorite not found");
    }

    @Override
    public User deleteFavorite(String email, int fid) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        List<Favorite> favorites = user.getFavoriteList();
        if (favorites == null || fid < 0 || fid >= favorites.size()) {
            throw new RuntimeException("Invalid favorite index");
        }

        favorites.remove(fid);
        user.setFavoriteList(favorites);

        return userRepository.save(user);
    }
}
