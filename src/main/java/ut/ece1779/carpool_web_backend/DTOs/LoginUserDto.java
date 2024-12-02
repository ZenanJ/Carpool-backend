package ut.ece1779.carpool_web_backend.DTOs;

public class LoginUserDto {
    private Long phoneNum;

    private String password;

    // getters and setters here...

    public String getPassword() {
        return password;
    }

    public Long getPhoneNum() {
        return phoneNum;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNum(Long phoneNum) {
        this.phoneNum = phoneNum;
    }
}