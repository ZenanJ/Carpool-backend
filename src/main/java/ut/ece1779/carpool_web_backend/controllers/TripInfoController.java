package ut.ece1779.carpool_web_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ut.ece1779.carpool_web_backend.DTOs.TripInfoDto;
import ut.ece1779.carpool_web_backend.models.TripInfo;
import ut.ece1779.carpool_web_backend.models.TripOwnership;
import ut.ece1779.carpool_web_backend.models.User;
import ut.ece1779.carpool_web_backend.models.Vehicle;
import ut.ece1779.carpool_web_backend.services.TripInfoService;
import ut.ece1779.carpool_web_backend.services.UserService;
import ut.ece1779.carpool_web_backend.services.VehicleService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/carpool-trips")
public class TripInfoController {

    private final VehicleService vehicleService;
    private final TripInfoService tripInfoService;
    private final UserService userService;
    private static final Logger logger = Logger.getLogger(TripInfoController.class.getName());

    // Constructor injection
    public TripInfoController(VehicleService vehicleService,
                              TripInfoService tripInfoService,
                              UserService userService) {
        // System.out.println("TripInfoController init");
        logger.info("TripInfoController init");
        this.vehicleService = vehicleService;
        this.tripInfoService = tripInfoService;
        this.userService = userService;
    }
    @GetMapping("/active-driver-posts")
    public ResponseEntity<List<TripInfoDto>> getActiveDriverPosts() {
        List<TripInfo> activeDriverPosts = tripInfoService.findActiveDriverPosts();

        // Convert entities to DTOs for response
        List<TripInfoDto> response = activeDriverPosts.stream()
                .map(tripInfo -> {
                            Vehicle vehicle = vehicleService.findByVehicleId(tripInfo.getVehicle());
                            User driver = userService.findById(tripInfo.getDriver());

                            return  new TripInfoDto(
                            tripInfo.getTripID(),
                            tripInfo.getDeparture(),
                            tripInfo.getDestination(),
                            tripInfo.getDepartureTime(),
                            tripInfo.getSeatsAvailable(),
                            tripInfo.getPrice(),
                            tripInfo.getDriverName(),
                                    driver,
                                    vehicle,
                            tripInfo.getIsDriverPost(),
                            tripInfo.getIsActivate()
                    );
                }
                )
                .toList();
                logger.info("returned list size: " + response.size());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/active-passenger-posts")
    public ResponseEntity<List<TripInfoDto>> getActivePassengerPosts() {
        List<TripInfo> activePassengerPosts = tripInfoService.findActivePassengerPosts();

        // Convert entities to DTOs for response
        List<TripInfoDto> response = activePassengerPosts.stream()
                .map(tripInfo -> {
                            Vehicle vehicle = vehicleService.findByVehicleId(tripInfo.getVehicle());
                            User driver = userService.findById(tripInfo.getDriver());

                            return  new TripInfoDto(
                                    tripInfo.getTripID(),
                                    tripInfo.getDeparture(),
                                    tripInfo.getDestination(),
                                    tripInfo.getDepartureTime(),
                                    tripInfo.getSeatsAvailable(),
                                    tripInfo.getPrice(),
                                    tripInfo.getDriverName(),
                                    driver,
                                    vehicle,
                                    tripInfo.getIsDriverPost(),
                                    tripInfo.getIsActivate()
                            );
                        }
                )
                .toList();
        logger.info("returned list size: " + response.size());
        return ResponseEntity.ok(response);
    }
    // Add a driver posting
    @PostMapping("/add-post")
    public ResponseEntity<?> addPosting(@RequestBody TripInfoDto tripInfoDTO, @RequestParam long userId) {
//        System.out.println("get add post request");
        logger.info("get add post request");
        Map<String, Object> response = new HashMap<>();

        try{
            User driver = userService.findByEmail(tripInfoDTO.getDriver().getEmail());
            logger.info("findByEmail finished");
            if (driver == null) {
                logger.info("Driver not found.");
                response.put("success", false);
                response.put("message", "Driver not found.");
                return ResponseEntity.badRequest().body(response);
            }

            // Search for vehicle by plate number
            Vehicle vehicle = vehicleService.findByPlateNum(
                    tripInfoDTO.getVehicle().getPlateNum(),
                    tripInfoDTO.getVehicle().getModel(),
                    tripInfoDTO.getVehicle().getBrand(),
                    tripInfoDTO.getVehicle().getColor(),
                    tripInfoDTO.getVehicle().getType()
            );
            logger.info("findByPlateNum finished");
            if (vehicle == null) {
                response.put("success", false);
                response.put("message", "Vehicle not found.");
                return ResponseEntity.badRequest().body(response);
            }

            // Create and populate TripInfo entity
            TripInfo tripInfo = new TripInfo();
            tripInfo.setDeparture(tripInfoDTO.getDeparture());
            tripInfo.setDestination(tripInfoDTO.getDestination());
            tripInfo.setDepartureTime(tripInfoDTO.getDepartureTime());
            tripInfo.setSeatsAvailable(tripInfoDTO.getSeatsAvailable());
            tripInfo.setPrice(tripInfoDTO.getPrice());
            tripInfo.setDriverName(tripInfoDTO.getDriverName());
            tripInfo.setDriver(driver.getId()); // Set the driver object
            tripInfo.setVehicle(vehicle.getVehicleId()); // Set the vehicle object
            tripInfo.setIsDriverPost(tripInfoDTO.isDriverPost());
            logger.info("isActivate: " + tripInfoDTO.isActivate());
            tripInfo.setIsActivate(tripInfoDTO.isActivate());

            // Save the trip info to the database
            tripInfoService.saveTrip(tripInfo);
            logger.info("saveTrip finished");

            // Create and populate TripOwnership entity
            TripOwnership tripOwnership = new TripOwnership();
            tripOwnership.setTrip(tripInfo.getTripID()); // Set the trip ID
            tripOwnership.setUser(userId); // Set the driver ID (user ID)
            tripOwnership.setIsDriver(tripInfoDTO.isDriverPost());
            tripOwnership.setPostOwnerId(userId);
            // Assuming you have a service for TripOwnership
            if(tripInfoService.saveTripOwnership(tripOwnership) == null){
                response.put("success", false);
                response.put("message", "Add ownership failed.");
                return ResponseEntity.badRequest().body(response);
            }
            logger.info("saveTripOwnership finished");

            response.put("success", true);
            response.put("message", "Trip successfully added.");
            return ResponseEntity.ok(response);
        }catch(Exception e) {
            // Handle other unexpected exceptions
            logger.warning("error here: " + e);
            response.put("success", false);
            response.put("message", "An error occurred while adding the trip: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }

    @GetMapping("/get-user-trip-history")
    public ResponseEntity<?> getActivePassengerPosts(@RequestParam long userId) {
        logger.info("get Active Passenger Posts");
        Map<String, Object> errResponse = new HashMap<>();
        if(userId == 0) {
            errResponse.put("success", false);
            errResponse.put("message", "userId is empty ");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errResponse);
        }
        try{
            List<TripInfo> activePassengerPosts = tripInfoService.findHistoryPostsByUserId(userId);
            List<TripInfoDto> response = activePassengerPosts.stream()
                    .map(tripInfo -> {
                                Vehicle vehicle = vehicleService.findByVehicleId(tripInfo.getVehicle());
                                User driver = userService.findById(tripInfo.getDriver());

                                return  new TripInfoDto(
                                        tripInfo.getTripID(),
                                        tripInfo.getDeparture(),
                                        tripInfo.getDestination(),
                                        tripInfo.getDepartureTime(),
                                        tripInfo.getSeatsAvailable(),
                                        tripInfo.getPrice(),
                                        tripInfo.getDriverName(),
                                        driver,
                                        vehicle,
                                        tripInfo.getIsDriverPost(),
                                        tripInfo.getIsActivate()
                                );
                            }
                    )
                    .toList();
            logger.info("returned list size: " + response.size());
            return ResponseEntity.ok(response);
        }catch(Exception e) {
            // Handle other unexpected exceptions
            logger.warning("error here: " + e);
            errResponse.put("success", false);
            errResponse.put("message", "An error occurred while adding the trip: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errResponse);
        }
    }

    @PutMapping("/expire")
    public ResponseEntity<?> expireTrip(@RequestParam Long tripId, @RequestBody Map<String, Object> updates) {
        logger.info("Received request to update trip status for tripId: {}" + tripId);
        Map<String, Object> response = new HashMap<>();

        if (!updates.containsKey("isActivate")) {
            response.put("success", false);
            response.put("message", "Missing 'isActivate' field in request.");
            return ResponseEntity.badRequest().body(response);
        }

        boolean isActivate = (Boolean) updates.get("isActivate");

        try {
            // Attempt to update trip status
            tripInfoService.updateTripStatus(tripId, isActivate);
            response.put("success", true);
            response.put("message", "Trip status updated successfully.");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            logger.warning("Error updating trip status: " + e.getMessage());
            response.put("success", false);
            response.put("message", "Error updating trip status: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            logger.severe("Unexpected error: " + e.getMessage());
            response.put("success", false);
            response.put("message", "An unexpected error occurred.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}

