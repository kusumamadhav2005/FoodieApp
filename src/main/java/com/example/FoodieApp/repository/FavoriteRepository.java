package com.example.FoodieApp.repository;

import com.example.FoodieApp.domain.Favorite;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FavoriteRepository extends MongoRepository<Favorite, String> {
}
