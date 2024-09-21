/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author nguye
 */
public class Users {

    private int userId;
    private String fullName;
    private String address;
    private String phone;
    private String emmail;
    private String password;
    private Roles roleId;
    private int otp;

    public Users() {
    }

    public Users(int userId, String fullName, String address, String phone, String emmail, String password, Roles roleId, int otp) {
        this.userId = userId;
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
        this.emmail = emmail;
        this.password = password;
        this.roleId = roleId;
        this.otp = otp;
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
        return emmail;
    }

    public void setEmmail(String emmail) {
        this.emmail = emmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Roles getRoleId() {
        return roleId;
    }

    public void setRoleId(Roles roleId) {
        this.roleId = roleId;
    }

    public int getOtp() {
        return otp;
    }

    public void setOtp(int otp) {
        this.otp = otp;
    }

    @Override
    public String toString() {
        return "Users{" + "userId=" + userId + ", fullName=" + fullName + ", address=" + address + ", phone=" + phone + ", emmail=" + emmail + ", password=" + password + ", roleId=" + roleId + ", otp=" + otp + '}';
    }

    
    
    
    
}
