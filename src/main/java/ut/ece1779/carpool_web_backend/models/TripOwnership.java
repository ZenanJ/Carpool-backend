package ut.ece1779.carpool_web_backend.models;

import jakarta.persistence.*;

@Entity
@Table(name = "trip_ownership")
public class TripOwnership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT UNSIGNED")
    private Long id;

//    @ManyToOne
    @Column(name = "user_id", nullable = false, columnDefinition = "BIGINT UNSIGNED")
    private Long user;

//    @ManyToOne
    @Column(name = "trip_id", nullable = false, columnDefinition = "BIGINT UNSIGNED")
    private Long trip;

    @Column(name = "is_driver", nullable = false)
    private Boolean isDriver;

    @Column(name = "post_owner_id", nullable = false, columnDefinition = "BIGINT UNSIGNED")
    private Long postOwnerId;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Long getTrip() {
        return trip;
    }

    public void setTrip(Long trip) {
        this.trip = trip;
    }

    public Boolean getIsDriver() {
        return isDriver;
    }

    public void setIsDriver(Boolean isDriver) {
        this.isDriver = isDriver;
    }

    public Long getPostOwnerId() {
        return postOwnerId;
    }

    public void setPostOwnerId(Long postOwnerId) {
        this.postOwnerId = postOwnerId;
    }
}
