
package model;

import java.util.Date;

public class Users {

    private int userId;
    private String fullName;
    private String address;
    private String phone;
    private String email;
    private String password;
    private int otp;
    private Date createdAt;
    
    private String role;

    public Users(int userID, String fullname_found, String email_found, String phone_found, String role) {
        this.userId = userID;
        this.fullName = fullname_found;
        this.email = email_found;
        this.phone = phone_found;
        this.role = role;
    }

    public Users() {
    }

    public Users(int userId, String fullName, String address, String phone, String emmail, String password, int otp, Date createdAt) {
        this.userId = userId;
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
        this.email = emmail;
        this.password = password;
        this.otp = otp;
        this.createdAt = createdAt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmmail() {
        return email;
    }

    public void setEmmail(String emmail) {
        this.email = emmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getOtp() {
        return otp;
    }

    public void setOtp(int otp) {
        this.otp = otp;
    }

    @Override
    public String toString() {
        return "Users{" + "userId=" + userId + ", fullName=" + fullName + ", address=" + address + ", phone=" + phone + ", emmail=" + email + ", password=" + password + ", otp=" + otp + ", createdAt=" + createdAt + '}';
    }

}
