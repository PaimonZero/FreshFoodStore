
package model;

import java.util.Date;

public class Supplier {
    private String name;
    private String phone;
    private String email;
    private String address;
    private String moreInfo;
    private Date createdAt;
    private int supplierId;

    public Supplier() {
    }

    public Supplier(int supplierId, String name, String number, String email, String address, String moreInfo, Date createdAt) {
        this.supplierId = supplierId;
        this.name = name;
        this.phone = number;
        this.email = email;
        this.address = address;
        this.moreInfo = moreInfo;
        this.createdAt = createdAt;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    
    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
