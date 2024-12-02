package ut.ece1779.carpool_web_backend.DTOs;

import ut.ece1779.carpool_web_backend.models.Vehicle;
import ut.ece1779.carpool_web_backend.models.User;

public class TripInfoDto {

    private Long tripID;              // Unique identifier for the trip
    private String departure;           // Departure location
    private String destination;         // Destination location
    private String departureTime;       // Departure time (use string if it needs formatting)
    private int seatsAvailable;         // Number of seats available
    private double price;               // Price of the trip
    private String driverName;          // Name of the driver
    private User driver;              // Driver's personal information (DTO)
    private Vehicle vehicle;            // Vehicle information for the trip
    private boolean isDriverPost;       // Is the post by driver?
    private boolean isActivate;         // Is the post activated?

    public TripInfoDto(Long tripID, String departure, String destination, String departureTime, int seatsAvailable, double price, String driverName, User driver, Vehicle vehicle, boolean isDriverPost, boolean isActivate) {
        this.tripID = tripID;
        this.departure = departure;
        this.destination = destination;
        this.departureTime = departureTime;
        this.seatsAvailable = seatsAvailable;
        this.price = price;
        this.driverName = driverName;
        this.driver = driver;
        this.vehicle = vehicle;
        this.isDriverPost = isDriverPost;
        this.isActivate = isActivate;
    }

    // Getters and setters
    public Long getTripID() {
        return tripID;
    }

    public void setTripID(Long tripID) {
        this.tripID = tripID;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public void setSeatsAvailable(int seatsAvailable) {
        this.seatsAvailable = seatsAvailable;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public User getDriver() {
        return driver;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public boolean isDriverPost() {
        return isDriverPost;
    }

    public void setDriverPost(boolean driverPost) {
        isDriverPost = driverPost;
    }

    public boolean isActivate() {
        return isActivate;
    }

    public void setActivate(boolean activate) {
        isActivate = activate;
    }


}
