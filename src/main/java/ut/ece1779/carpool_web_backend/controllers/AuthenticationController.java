package ut.ece1779.carpool_web_backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ut.ece1779.carpool_web_backend.DTOs.LoginUserDto;
import ut.ece1779.carpool_web_backend.DTOs.RegisterUserDto;
import ut.ece1779.carpool_web_backend.models.User;
import ut.ece1779.carpool_web_backend.services.AuthenticationService;
import ut.ece1779.carpool_web_backend.services.JwtService;
import ut.ece1779.carpool_web_backend.responses.LoginResponse;

import javax.naming.AuthenticationException;
import java.util.logging.Logger;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;
    private static final Logger logger = Logger.getLogger(TripInfoController.class.getName());
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
//    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
    public ResponseEntity<?> authenticate(@RequestBody LoginUserDto loginUserDto) {
        logger.info("calling authenticate");
        try {
            // Attempt to authenticate the user
            User authenticatedUser = authenticationService.authenticate(loginUserDto);

            // Generate JWT token upon successful authentication
            String jwtToken = jwtService.generateToken(authenticatedUser);

            // Create the login response
            LoginResponse loginResponse = new LoginResponse()
                    .setToken(jwtToken)
                    .setExpiresIn(jwtService.getExpirationTime())
                    .setId(authenticatedUser.getId())
                    .setPhoneNum(authenticatedUser.getPhoneNum())
                    .setFirstName(authenticatedUser.getFirstName())
                    .setLastName(authenticatedUser.getLastName())
                    .setEmail(authenticatedUser.getEmail());

            // Return the response with HTTP 200 OK
            return ResponseEntity.ok(loginResponse);

        } catch (Exception e) {
            // Handle other unexpected exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred. Please try again later.");
        }
    }
}
