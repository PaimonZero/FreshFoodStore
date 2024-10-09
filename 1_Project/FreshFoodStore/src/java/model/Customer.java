
package model;

public class Customer {
    private int customerId;
    private int userId;
    //Thiện add
    private String fullName;
    private String phone;
    private String email;
    private String address;
    private String status;
    //Nam thêm
    private String avatar;

    public Customer() {
    }
    
    public Customer(int customerId, String name, String phone, String email, String address, String status, int userId) {
        this.customerId = customerId;
        this.fullName = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.status = status;
        this.userId = userId;
    }

    public Customer(int customerId, int userId) {
        this.customerId = customerId;
        this.userId = userId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Customer{" + "customerId=" + customerId + ", userId=" + userId + '}';
    }
    
}
