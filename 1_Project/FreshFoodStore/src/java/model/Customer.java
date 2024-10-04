
package model;

public class Customer {
    private int customerId;
    private int userId;

    public Customer() {
    }

    public Customer(int customerId, int userId) {
        this.customerId = customerId;
        this.userId = userId;
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
