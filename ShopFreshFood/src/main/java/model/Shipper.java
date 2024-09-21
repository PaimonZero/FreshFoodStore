/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author nguye
 */
public class Shipper {

    private int shipperId;
    private String fullName;
    private String address;
    private String phone;
    private String emmail;
    private String password;
    private Roles roleId;
    private int otp;

    public Shipper() {
    }

    public Shipper(int shipperId, String fullName, String address, String phone, String emmail, String password, Roles roleId, int otp) {
        this.shipperId = shipperId;
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
        this.emmail = emmail;
        this.password = password;
        this.roleId = roleId;
        this.otp = otp;
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

    

    public int getShipperId() {
        return shipperId;
    }

    public void setShipperId(int shipperId) {
        this.shipperId = shipperId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "Shippers{" + "shipperId=" + shipperId + ", fullName=" + fullName + ", address=" + address + ", phone=" + phone + ", emmail=" + emmail + ", password=" + password + ", roleId=" + roleId + ", otp=" + otp + '}';
    }

  
}
