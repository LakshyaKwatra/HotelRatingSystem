package hotel.rating.system;

import java.util.*;

public class Driver {
    public static HashMap<String, List<String>> hotelData = new HashMap<>(); //hotel_id : {name, label} //assumed as given
    public static HashMap<String, Hotel> hotels = new HashMap<>();
    public static HashMap<String, User> users = new HashMap<>();

    public static void main(String[] args) {
        initializeHotels();
        HotelRatingService hotelRatingService = new HotelRatingService();
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String command = scanner.nextLine();
            String[] commandSplit = command.split(" ");
            switch (commandSplit[0]) {
                case "ADD_USER":
                    hotelRatingService.addUser(commandSplit[1]);
                case "ADD_RATING":
                    if(commandSplit.length == 4) {
                        hotelRatingService.addRating(Integer.parseInt(commandSplit[1]), commandSplit[2], commandSplit[3], null);
                    } else if (commandSplit.length == 5) {
                        hotelRatingService.addRating(Integer.parseInt(commandSplit[1]), commandSplit[2], commandSplit[3], commandSplit[4]);
                    }
                    break;
                case "GET_RATINGS":
                    hotelRatingService.getRatings(commandSplit[1], commandSplit[2], commandSplit[3]);
                    break;
                case "DESCRIBE_HOTEL":
                    hotelRatingService.describeHotel(commandSplit[1]);
                    break;
                case "LIST_HOTELS":
                    hotelRatingService.listHotels();
                    break;
            }
        }

    }

    private static void initializeHotels() {
        hotelData.put("1", new ArrayList<>(Arrays.asList("OYO")));
        hotelData.put("2", new ArrayList<>(Arrays.asList("Ibis", "Plus_Hotel")));
        hotelData.put("3", new ArrayList<>(Arrays.asList("HotelX")));
        hotelData.put("4", new ArrayList<>(Arrays.asList("HotelY", "Plus_Hotel")));
        for(String hotelId: hotelData.keySet()) {
            Hotel hotel = new Hotel(hotelId, hotelData.get(hotelId).get(0));
            if(hotelData.get(hotelId).size() == 2) {
                hotel.setLabel(hotelData.get(hotelId).get(1));
            }
            hotels.put(hotelId, hotel);
        }
    }

}