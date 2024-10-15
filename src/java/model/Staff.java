
package model;

public class Staff {
    private int staffId;
    private int userId;

    public Staff() {
    }

    public Staff(int staffId, int userId) {
        this.staffId = staffId;
        this.userId = userId;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Staff{" + "staffId=" + staffId + ", userId=" + userId + '}';
    }
    
    
}
