
package dto;

public class ShipperDTO {
    public int shipperId;
    public int userId;
    public String fullName;
    public String phone;
    public String status;

    public ShipperDTO() {
    }

    public ShipperDTO(int shipperId, int userId, String fullName, String phone, String status) {
        this.shipperId = shipperId;
        this.userId = userId;
        this.fullName = fullName;
        this.phone = phone;
        this.status = status;
    }
    
    public ShipperDTO(int shipperId, int userId, String fullName, String phone) {
        this.shipperId = shipperId;
        this.userId = userId;
        this.fullName = fullName;
        this.phone = phone;
    }

    public int getShipperId() {
        return shipperId;
    }

    public void setShipperId(int shipperId) {
        this.shipperId = shipperId;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
