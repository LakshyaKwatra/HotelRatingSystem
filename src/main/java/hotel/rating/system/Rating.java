package hotel.rating.system;

import java.util.Date;

public class Rating {
    int rating;
    String review;
    String hotelId;
    String userId;
    Date time;

    public Rating(int rating, String review, String hotelId, String userId, Date time) {
        this.rating = rating;
        this.review = review;
        this.hotelId = hotelId;
        this.userId = userId;
        this.time = time;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
