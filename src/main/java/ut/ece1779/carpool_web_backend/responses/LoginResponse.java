package ut.ece1779.carpool_web_backend.responses;

import org.apache.juli.logging.Log;
import ut.ece1779.carpool_web_backend.models.User;

public class LoginResponse {
    private String token;
    private long expiresIn;
    private Long id;
    private Long phoneNum;
    private String firstName;
    private String lastName;
    private String email;

    public String getToken() {
        return token;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }

    public Long getPhoneNum() {
        return phoneNum;
    }

    public LoginResponse setPhoneNum(Long phoneNum) {
        this.phoneNum = phoneNum;
        return this;
    }

    public LoginResponse setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public LoginResponse setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public LoginResponse setEmail(String email) {
        this.email = email;
        return this;
    }

    public LoginResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public LoginResponse setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
        return this;
    }

    public LoginResponse setToken(String token) {
        this.token = token;
        return this;
    }
}
