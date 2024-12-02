package ut.ece1779.carpool_web_backend.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ut.ece1779.carpool_web_backend.models.TripInfo;
import ut.ece1779.carpool_web_backend.models.TripOwnership;
import ut.ece1779.carpool_web_backend.models.User;

import java.util.List;

@Repository
public interface TripOwnershipRepository extends CrudRepository<TripOwnership, Long> {
    List<TripOwnership> findByUser(Long id);
    List<TripOwnership> findByTrip(Long id);
}
