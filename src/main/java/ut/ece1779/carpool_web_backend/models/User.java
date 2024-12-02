package ut.ece1779.carpool_web_backend.models;

import jakarta.persistence.*;
import org.apache.juli.logging.Log;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "users")
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;  // Changed to Long

    @Column(nullable = false)
    private Long phoneNum;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)  // Ensure email is unique
    private String email;

    @Column(nullable = false)
    private String password;  // You should hash this!

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();  // Example authority
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return String.valueOf(phoneNum);
    }

    public Long getId() {
        return id;
    }

    // Complete Getters and Setters
    public Long getPhoneNum() {
        return phoneNum;
    }

    public User setPhoneNum(Long phoneNum) {
        this.phoneNum = phoneNum;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
