package com.example.FoodieApp.service;

import com.example.FoodieApp.domain.Favorite;
import com.example.FoodieApp.domain.User;
import com.example.FoodieApp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User register(User user) {
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new RuntimeException("Email is required");
        }
        if (repository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("User already exists");
        }
        if (user.getFavoriteList() == null) {
            user.setFavoriteList(new ArrayList<>());
        }
        if (user.getProfileImage() == null) {
            user.setProfileImage(new byte[0]);
        }
        return repository.save(user);
    }


    @Override
    public User login(String email, String password) {
        return repository.findByEmailAndPassword(email, password);
    }

    @Override
    public User addFavorite(String email, Favorite favorite) {
        User user = repository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        List<Favorite> favorites = user.getFavoriteList();
        if (favorites == null) {
            favorites = new ArrayList<>();
        }

        favorites.add(favorite);
        user.setFavoriteList(favorites);

        return repository.save(user);
    }

    @Override
    public List<Favorite> getFavorites(String email) {
        User user = repository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return user.getFavoriteList();
    }
}
