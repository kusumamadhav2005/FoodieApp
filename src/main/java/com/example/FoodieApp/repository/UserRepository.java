package com.example.FoodieApp.repository;

import com.example.FoodieApp.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    User findByEmailAndPassword(String email, String password);
    User findByEmail(String email);
}
