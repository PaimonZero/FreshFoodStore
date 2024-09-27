
package model;

public class Manager {
    private int managerId;
    private int userId;

    public Manager() {
    }

    public Manager(int managerId, int userId) {
        this.managerId = managerId;
        this.userId = userId;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Manager{" + "managerId=" + managerId + ", userId=" + userId + '}';
    }
    
}
