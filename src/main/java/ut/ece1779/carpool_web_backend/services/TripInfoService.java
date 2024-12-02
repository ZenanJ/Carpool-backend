package ut.ece1779.carpool_web_backend.services;
import org.springframework.stereotype.Service;
import ut.ece1779.carpool_web_backend.models.TripInfo;
import ut.ece1779.carpool_web_backend.models.TripOwnership;
import ut.ece1779.carpool_web_backend.repositories.TripInfoRepository;
import ut.ece1779.carpool_web_backend.repositories.TripOwnershipRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TripInfoService {
    private final TripInfoRepository tripInfoRepository;
    private final TripOwnershipRepository tripOwnershipRepository;

    public TripInfoService(
            TripInfoRepository tripInfoRepository,
            TripOwnershipRepository tripOwnershipRepository) {
        this.tripInfoRepository = tripInfoRepository;
        this.tripOwnershipRepository = tripOwnershipRepository;
    }

    public String saveTrip(TripInfo tripInfo) {
        TripInfo isSuccess = tripInfoRepository.save(tripInfo);

        if (isSuccess != null) {
            return "TripInfo saved successfully!";
        } else {
            return "Failed to save TripInfo.";
        }
    }

    public List<TripInfo> findActiveDriverPosts() {
        return tripInfoRepository.findByIsActivateTrueAndIsDriverPostTrue();
    }
    public List<TripInfo> findActivePassengerPosts() {
        return tripInfoRepository.findByIsActivateTrueAndIsDriverPostFalse();
    }

    public List<TripOwnership> findHistoryPostsIdByUserId(Long user_id) {
        return tripOwnershipRepository.findByUser(user_id);
    }

    public List<TripOwnership> findUserIdByHistoryPostsId(Long trip_id) {
        return tripOwnershipRepository.findByTrip(trip_id);
    }

    public TripOwnership saveTripOwnership(TripOwnership tripOwnership) {
        return tripOwnershipRepository.save(tripOwnership);
    }

    public List<TripInfo> findHistoryPostsByUserId(Long user_id) {
        // Step 1: Retrieve all TripOwnership records for the user
        List<TripOwnership> ownerships = findHistoryPostsIdByUserId(user_id);

        // Step 2: Extract trip IDs from the ownerships
        List<Long> tripIds = ownerships.stream()
                .map(TripOwnership::getTrip)
                .collect(Collectors.toList());

        // Step 3: Fetch all TripInfo records for the extracted trip IDs
        List<TripInfo> tripInfos = tripInfoRepository.findAllById(tripIds);

        // Step 4: Return the TripInfo list
        return tripInfos;
    }

    public void updateTripStatus(Long tripId, boolean isActivate) {
        TripInfo trip = tripInfoRepository.findByTripID(tripId);
        trip.setIsActivate(isActivate); // Assuming there's a `setActive` method in the `Trip` entity
        tripInfoRepository.save(trip);
    }
}
