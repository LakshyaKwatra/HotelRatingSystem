package hotel.rating.system;

import java.util.HashMap;

public class User {
    String userId;
    HashMap<String, Integer> ratings;
    HashMap<String, String> reviews;

    public User(String userId) {
        this.userId = userId;
        this.ratings = new HashMap<>(); // hotelid, reviews
        this.reviews = new HashMap<>();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public HashMap<String, Integer> getRatings() {
        return ratings;
    }

    public void setRatings(HashMap<String, Integer> ratings) {
        this.ratings = ratings;
    }

    public HashMap<String, String> getReviews() {
        return reviews;
    }

    public void setReviews(HashMap<String, String> reviews) {
        this.reviews = reviews;
    }
}

