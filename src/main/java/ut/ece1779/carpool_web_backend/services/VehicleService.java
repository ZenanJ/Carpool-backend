package ut.ece1779.carpool_web_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ut.ece1779.carpool_web_backend.models.Vehicle;
import ut.ece1779.carpool_web_backend.repositories.VehicleRepository;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository){
        this.vehicleRepository = vehicleRepository;
    }
    // Find vehicle by plate number
//    public Vehicle findByPlateNum(String plateNum) {
    public Vehicle findByPlateNum(String plateNum, String model, String brand, String color, String type) {
        // Check if vehicle exists by plate number
        return vehicleRepository.findByPlateNum(plateNum).orElseGet(() -> {
            // If vehicle doesn't exist, create a new one
            Vehicle newVehicle = new Vehicle();
            newVehicle.setPlateNum(plateNum);
            newVehicle.setModel(model);
            newVehicle.setBrand(brand);
            newVehicle.setColor(color);
            newVehicle.setType(type);
            return vehicleRepository.save(newVehicle); // Save and return the new vehicle
        });
    }

    public Vehicle findByVehicleId(Long id) {
        // Check if vehicle exists by plate number
        return vehicleRepository.findByVehicleId(id).orElseThrow(() -> new IllegalArgumentException("Vehicle not found for ID: " + id));
    }

}
