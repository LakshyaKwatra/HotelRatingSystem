package hotel.rating.system;

import java.text.DecimalFormat;
import java.util.*;

public class Hotel {
    String id;
    String name;
    HashMap<String, Integer> ratings; //userId: ratings
    HashMap<String, Date> ratingLastUpdated; // userId: Date
    HashMap<String, String> reviews; //userId: reviews
    HashMap<String, Rating> ratingList;
    String label;

    public Hotel(String id, String name) {
        this.id = id;
        this.name = name;
        this.label = null;
        this.ratingList = new HashMap<>();
        this.ratings = new HashMap<>();
        this.reviews = new HashMap<>();
        this.ratingLastUpdated = new HashMap<>();
    }

    public HashMap<String, Date> getRatingLastUpdated() {
        return ratingLastUpdated;
    }

    public void setRatingLastUpdated(HashMap<String, Date> ratingLastUpdated) {
        this.ratingLastUpdated = ratingLastUpdated;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public double getAverageRating() {
        Collection<Integer> values = ratings.values();
        int n = values.size();
        if(n == 0) {
            return -1;
        }
        int sum = 0;
        for(Integer value: values) {
            sum += value;
        }
        double avg = sum/n;
        DecimalFormat df = new DecimalFormat("#.#");
        String formatted = df.format(avg);
        return Double.parseDouble(formatted);
    }

    public HashMap<String, Rating> getRatingList() {
        return ratingList;
    }

    public void setRatingList(HashMap<String, Rating> ratingList) {
        this.ratingList = ratingList;
    }
}
