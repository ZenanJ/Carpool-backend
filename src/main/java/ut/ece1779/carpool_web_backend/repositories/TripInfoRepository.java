package ut.ece1779.carpool_web_backend.repositories;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import jakarta.transaction.Transactional;
//import org.springframework.stereotype.Repository;
//import ut.ece1779.carpool_web_backend.models.TripInfo;
//import ut.ece1779.carpool_web_backend.models.Vehicle;
//
//@Repository
//public class TripInfoRepository {
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Transactional
//    public boolean save(TripInfo tripInfo) {
//        try {
//            // Use persist for new entities or merge for updates
//            if (tripInfo.getTripID() == null) {
//                entityManager.persist(tripInfo); // Save new entity
//            } else {
//                entityManager.merge(tripInfo);   // Update existing entity
//            }
//            return true; // Operation successful
//        } catch (Exception e) {
//            e.printStackTrace(); // Log the exception for debugging
//            return false;        // Operation failed
//        }
//    }
//
//}
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ut.ece1779.carpool_web_backend.models.TripInfo;

import java.util.List;

@Repository
public interface TripInfoRepository extends CrudRepository<TripInfo, Long> {

    // Custom query methods if needed
    // For example:
    // List<TripInfo> findByIsActivateTrueAndIsDriverPostTrue();
    List<TripInfo> findByIsActivateTrueAndIsDriverPostTrue();

    List<TripInfo> findByIsActivateTrueAndIsDriverPostFalse();

    List<TripInfo> findAllById(Iterable<Long> ids);

    TripInfo findByTripID(Long Id);
}