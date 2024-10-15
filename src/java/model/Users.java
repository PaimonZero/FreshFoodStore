
package model;

import java.util.Date;

public class Users {

    private int userId;
    private String fullName;
    private String address;
    private String phone;
    private String email;
    private String password;
    private String passGoogle;
    private Date createdAt;
    private String avatar;
    //not in db
    private String role;

    public Users(int userID, String fullname, String email, String phone, String address, String role) {
        this.userId = userID;
        this.fullName = fullname;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.role = role;
    }

    public Users(int userID, String fullname, String email, String phone, String address) {
        this.userId = userID;
        this.fullName = fullname;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public Users(int userId, String fullName, String address, String phone, String email, String password, String avatar) {
        this.userId = userId;
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
    }
    
    public Users() {
    }

    public Users(int userId, String fullName, String address, String phone, String email, String password, Date createdAt) {
        this.userId = userId;
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
    }

    public String getPassGoogle() {
        return passGoogle;
    }

    public void setPassGoogle(String passGoogle) {
        this.passGoogle = passGoogle;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

//    @Override
//    public String toString() {
//        return "Users{" + "userId=" + userId+ ", role=" + role + ", fullName=" + fullName + ", address=" + address + ", phone=" + phone + ", emmail=" + email + ", password=" + password + ", otp=" + otp + ", createdAt=" + createdAt + '}';
//    }

    @Override
    public String toString() {
        return "Users{" + "userId=" + userId + ", fullName=" + fullName + ", address=" + address + ", phone=" + phone + ", email=" + email + ", password=" + password + ", avatar=" + avatar + '}';
    }
    
}
