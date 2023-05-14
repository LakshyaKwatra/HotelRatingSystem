package hotel.rating.system;

import java.util.*;

public class HotelRatingService {

    public void addUser(String userId) {
        if(Driver.users.containsKey(userId)) {
            return;
        }
        Driver.users.put(userId, new User(userId));
        System.out.println("user added");
    }
    public void addRating(Integer rating, String userId, String hotelId, String review) {
        Hotel hotel = Driver.hotels.get(hotelId);
        if(Objects.isNull(hotel)) {
            return;
        }
        addUser(userId);
        if(hotel.getRatingList().containsKey(userId)) {
            hotel.getRatingList().get(userId).setRating(rating);
        }
        hotel.getRatings().put(userId, rating); //userId: int rating
        hotel.getRatingList().put(userId, new Rating(rating, review,hotelId, userId, new Date()));
        Driver.users.get(userId).getRatings().put(hotelId, rating);
        if(Objects.nonNull(review)) {
            hotel.getReviews().put(userId, review);
            Driver.users.get(userId).getReviews().put(hotelId, review);
            hotel.getRatingList().get(userId).setReview(review);
        }
        hotel.getRatingLastUpdated().put(userId, new Date());
        System.out.println("Rating and review added successfully.");
    }

    public void describeHotel(String hotelId) {
        Hotel hotel = Driver.hotels.get(hotelId);
        if(Objects.isNull(hotel)) {
            return;
        }
        System.out.println("id = " + hotelId);
        System.out.println("name = " + hotel.getName());

        double averageRating = hotel.getAverageRating();
        String rating = "NA";
        if(averageRating >= 1) {
            rating = String.valueOf(averageRating);
        }
        System.out.println("rating = " + rating);
        if(Objects.nonNull(hotel.getLabel())) {
            System.out.println("label = " + hotel.getLabel());
        }
    }

    public void listHotels() {
        List<Hotel> hotels = new ArrayList<>(Driver.hotels.values().stream().toList());
        Collections.sort(hotels, Comparator.comparing(Hotel::getAverageRating).reversed());
        for(int i = 0; i < hotels.size(); i++) {
            System.out.println("Hotel id: " +hotels.get(i).getId() + ", Hotel name: " + hotels.get(i).getName());
        }
    }

    public void getRatings(String hotelId, String order, String filter) {
        Hotel hotel = Driver.hotels.get(hotelId);
        if(Objects.isNull(hotel)) {
            return;
        }
        OrderBy orderBy;
        try {
            orderBy = OrderBy.valueOf(order);
        } catch (Exception e) {
            orderBy = OrderBy.RATING_DESC;
        }
        FilterBy filterBy;
        try {
            filterBy = FilterBy.valueOf(filter);
        } catch (Exception e) {
            filterBy = null;
        }
        List<Rating> ratings = getRatingsByOrderAndFilter(orderBy, filterBy, hotel);
        for(Rating rating: ratings) {
            System.out.println(rating.getRating() +" " + rating.getUserId());
            if(Objects.nonNull(rating.getReview()))
                System.out.println(rating.getReview());
        }

    }

    private List<Rating> getRatingsByOrderAndFilter(OrderBy orderBy, FilterBy filterBy, Hotel hotel) {
        HashMap<String, Rating> hotelRatings = hotel.getRatingList();
        List<Rating> ratingList = new ArrayList<>(hotelRatings.values().stream().toList());
        if(orderBy.equals(OrderBy.RATING_DESC)) {
            Collections.sort(ratingList, Comparator.comparing(Rating::getRating).thenComparing(Rating::getTime).reversed());
        } else if(orderBy.equals(OrderBy.RATING_ASC)) {
            Collections.sort(ratingList, Comparator.comparing(Rating::getRating));
        } else if (orderBy.equals(OrderBy.RECENT)) {
            Collections.sort(ratingList, Comparator.comparing(Rating::getTime).reversed());
        }
        List<Rating>  res = new ArrayList<>();
        if(filterBy == null) {
            res = ratingList;
            return res;
        }
        for(Rating rating: ratingList) {
            if(filterBy.equals(FilterBy.LOW)) {
                if(rating.getRating() <= 2) {
                    res.add(rating);
                }
            }
            if(filterBy.equals(FilterBy.MID)) {
                if(rating.getRating() <= 4 && rating.getRating() >= 2) {
                    res.add(rating);
                }
            }
            if(filterBy.equals(FilterBy.HIGH)) {
                if(rating.getRating() == 5) {
                    res.add(rating);
                }
            }
        }
        return res;
    }
}
