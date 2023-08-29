package com.example.restobook;

public class Restaurant {
    String restaurant_name,restaurant_location,restaurant_rating;
    int restaurant_image;

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public String getRestaurant_location() {
        return restaurant_location;
    }

    public String getRestaurant_rating() {
        return restaurant_rating;
    }

    public int getRestaurant_image() {
        return restaurant_image;
    }

    public Restaurant(String restaurant_name, String restaurant_location, String restaurant_rating, int restaurant_image) {
        this.restaurant_name = restaurant_name;
        this.restaurant_location = restaurant_location;
        this.restaurant_rating = restaurant_rating;
        this.restaurant_image = restaurant_image;
    }
}

