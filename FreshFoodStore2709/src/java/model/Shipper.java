
package model;

public class Shipper {

    private int shipperId;
    private int userId;

    public Shipper() {
    }

    public Shipper(int shipperId, int userId) {
        this.shipperId = shipperId;
        this.userId = userId;
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

    @Override
    public String toString() {
        return "Shipper{" + "shipperId=" + shipperId + ", userId=" + userId + '}';
    }

}
