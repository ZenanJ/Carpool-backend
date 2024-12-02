package ut.ece1779.carpool_web_backend.services;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ut.ece1779.carpool_web_backend.DTOs.LoginUserDto;
import ut.ece1779.carpool_web_backend.DTOs.RegisterUserDto;
import ut.ece1779.carpool_web_backend.models.User;
import ut.ece1779.carpool_web_backend.repositories.UserRepository;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(RegisterUserDto input) {
        try {
            User user = new User()
                    .setFirstName(input.getFirstName())
                    .setLastName(input.getLastName())
                    .setEmail(input.getEmail())
                    .setPhoneNum(input.getPhoneNum())
                    .setPassword(passwordEncoder.encode(input.getPassword()));

            return userRepository.save(user);

        } catch (ConstraintViolationException | DataIntegrityViolationException e) {
            // Log the error (optional)
            System.err.println("Error during signup: " + e.getMessage());

            // You can throw a custom exception or return null, depending on your needs
            throw new RuntimeException("User signup failed due to invalid input: " + e.getMessage());
        }
    }

    public User authenticate(LoginUserDto input) {
        System.out.println("inside the authenticate");
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getPhoneNum(),
                        input.getPassword()
                )
        );

        return userRepository.findByPhoneNum(input.getPhoneNum())
                .orElseThrow();
    }
}