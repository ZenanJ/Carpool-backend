package ut.ece1779.carpool_web_backend.repositories;

import ut.ece1779.carpool_web_backend.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByPhoneNum(Long PhoneNum);

    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);
}