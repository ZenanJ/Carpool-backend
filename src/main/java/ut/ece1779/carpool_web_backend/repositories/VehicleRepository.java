package ut.ece1779.carpool_web_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ut.ece1779.carpool_web_backend.models.User;
import ut.ece1779.carpool_web_backend.models.Vehicle;

import java.util.Optional;

@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, Long> {

    // Custom query to find a vehicle by its plate number
    Optional<Vehicle> findByPlateNum(String plateNum);
    Optional<Vehicle> findByVehicleId(Long vehicle_id);
}