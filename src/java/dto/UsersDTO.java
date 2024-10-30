
package dto;

import java.util.Date;

public class UsersDTO {
    public int userId;
    public String fullName;
    public String address;
    public String phone;
    public String email;
    public String password;
    public String passGoogle;
    public String avatar;
    public Date createdAt;
    public String status;       //blocked or normal
    //ko có trong user và tên biến khác db
    public String roleName;       //là biến lưu trữ tên role (Customer, Staff, Shipper, Manager)
    public int roleId;            //là customerId, staffId, shipperId, managerId trong db

    public UsersDTO() {
    }
   
    public UsersDTO(int userId, String fullName, String address, String phone, String email, String password, String passGoogle, String avatar, Date createdAt, String status, String roleName, int roleId) {
        this.userId = userId;
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.passGoogle = passGoogle;
        this.avatar = avatar;
        this.createdAt = createdAt;
        this.status = status;
        this.roleName = roleName;
        this.roleId = roleId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassGoogle() {
        return passGoogle;
    }

    public void setPassGoogle(String passGoogle) {
        this.passGoogle = passGoogle;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "UsersDTO{" + "userId=" + userId + ", fullName=" + fullName + ", address=" + address + ", phone=" + phone + ", email=" + email + ", password=" + password + ", passGoogle=" + passGoogle + ", avatar=" + avatar + ", createdAt=" + createdAt + ", status=" + status + ", roleName=" + roleName + ", roleId=" + roleId + '}';
    }

}
