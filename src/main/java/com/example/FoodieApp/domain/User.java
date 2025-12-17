package com.example.FoodieApp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;
import java.util.List;

@Document(collection = "users")
public class User {

    @Id
    private String email;
    private String username;
    private String password;
    private String mobileno;
    private byte[] profileImage;
    private List<Favorite> favoriteList;

    public User() {}

    public User(String email, String username, String password,
                String mobileno, byte[] profileImage, List<Favorite> favoriteList) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.mobileno = mobileno;
        this.profileImage = profileImage;
        this.favoriteList = favoriteList;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public byte[] getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(byte[] profileImage) {
        this.profileImage = profileImage;
    }

    public List<Favorite> getFavoriteList() {
        return favoriteList;
    }

    public void setFavoriteList(List<Favorite> favoriteList) {
        this.favoriteList = favoriteList;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", mobileno='" + mobileno + '\'' +
                ", profileImage=" + Arrays.toString(profileImage) +
                ", favoriteList=" + favoriteList +
                '}';
    }
}
