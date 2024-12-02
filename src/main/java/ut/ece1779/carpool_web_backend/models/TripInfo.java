package ut.ece1779.carpool_web_backend.models;

import jakarta.persistence.*;

@Entity
@Table(name = "trip_info")
public class TripInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trip_id")
    private Long tripID; // Will be auto-generated

    @Column(name = "departure", nullable = false)
    private String departure;

    @Column(name = "destination", nullable = false)
    private String destination;

    @Column(name = "departure_time", nullable = false)
    private String departureTime;

    @Column(name = "seats_available", nullable = false)
    private int seatsAvailable;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "driver_name", nullable = false)
    private String driverName;

//    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "driver_personal_id", nullable = false)
    private Long driver; // User entity representing the driver

//    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "vehicle_id", nullable = false)
    private Long vehicle; // Vehicle entity representing the vehicle

    @Column(name = "is_driver_post", nullable = false)
    private boolean isDriverPost;

    @Column(name = "is_activate", nullable = false)
    private boolean isActivate;

    // Getters and Setters
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

    public Long getDriver() {
        return driver;
    }

    public void setDriver(Long driver) {
        this.driver = driver;
    }

    public Long getVehicle() {
        return vehicle;
    }

    public void setVehicle(Long vehicle) {
        this.vehicle = vehicle;
    }

    public boolean getIsDriverPost() {
        return isDriverPost;
    }

    public void setIsDriverPost(boolean driverPost) {
        isDriverPost = driverPost;
    }

    public boolean getIsActivate() {
        return isActivate;
    }

    public void setIsActivate(boolean activate) {
        isActivate = activate;
    }
}
