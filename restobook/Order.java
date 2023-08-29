package com.example.restobook;

public class Order {
    String restaurantName,bookingDate;
    int bookingGuests,restaurantImage;

    public Order(String restaurantName, String bookingDate, int bookingGuests, int restaurantImage) {
        this.restaurantName = restaurantName;
        this.bookingDate = bookingDate;
        this.bookingGuests = bookingGuests;
        this.restaurantImage = restaurantImage;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public int getRestaurantImage() {
        return restaurantImage;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public int getBookingGuests() {
        return bookingGuests;
    }
}
