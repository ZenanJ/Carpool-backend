package ut.ece1779.carpool_web_backend.DTOs;

public class RegisterUserDto {
    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private Long phoneNum;

    public Long getPhoneNum() {
        return phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNum(Long phoneNum) {
        this.phoneNum = phoneNum;
    }
}


