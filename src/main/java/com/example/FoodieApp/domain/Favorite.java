package com.example.FoodieApp.domain;

public class Favorite {

    private String restaurantId;
    private String restaurantName;
    private String cuisine;
    private String userEmail;

    public Favorite() {}

    public Favorite(String restaurantId, String restaurantName, String cuisine, String userEmail) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.cuisine = cuisine;
        this.userEmail = userEmail;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
        return "Favorite{" +
                "restaurantId='" + restaurantId + '\'' +
                ", restaurantName='" + restaurantName + '\'' +
                ", cuisine='" + cuisine + '\'' +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }
}
