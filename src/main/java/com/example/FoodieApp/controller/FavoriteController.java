package com.example.FoodieApp.controller;

import com.example.FoodieApp.domain.Favorite;
import com.example.FoodieApp.domain.User;
import com.example.FoodieApp.repository.FavoriteRepository;
import com.example.FoodieApp.service.FavoriteService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/favorites")
public class FavoriteController {

    private final FavoriteService service;
    private final FavoriteRepository favoriteRepository;

    @Value("${jwt.secret}")
    private String secret;

    public FavoriteController(FavoriteService service, FavoriteRepository favoriteRepository) {
        this.service = service;
        this.favoriteRepository = favoriteRepository;
    }

    @PostMapping
    public ResponseEntity<?> saveFavorite(@RequestBody Favorite favorite,
                                          @RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing or invalid Token");
        }
        try {
            String token = authHeader.substring(7);
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            String email = claims.getSubject();
            favorite.setUserEmail(email);
            Favorite saved = favoriteRepository.save(favorite);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired Token");
        }
    }

    @PostMapping("/add/{email}")
    public ResponseEntity<User> addFavorite(@PathVariable String email,
                                            @RequestBody Favorite favorite) {
        User user = service.addFavorite(email, favorite);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/{email}")
    public ResponseEntity<List<Favorite>> getFavorites(@PathVariable String email) {
        List<Favorite> favorites = service.getFavorites(email);
        return new ResponseEntity<>(favorites, HttpStatus.OK);
    }

    @PutMapping("/update/{email}")
    public ResponseEntity<User> updateFavorite(@PathVariable String email,
                                               @RequestBody Favorite favorite) {
        User user = service.updateFavorite(email, favorite);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{email}/{fid}")
    public ResponseEntity<User> deleteFavorite(@PathVariable String email,
                                               @PathVariable int fid) {
        User user = service.deleteFavorite(email, fid);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
