package ut.ece1779.carpool_web_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ut.ece1779.carpool_web_backend.models.User;
import ut.ece1779.carpool_web_backend.repositories.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService (
            UserRepository userRepository
    ){
        this.userRepository = userRepository;
    }
    // Find driver by email
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(); // Custom query in the repository (we'll define this)
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(); // Custom query in the repository (we'll define this)
    }
}
