/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author nguye
 */
public class Suppliers {

    private int supplierId;
    private String name;
    private String address;
    private String phone;
    private String emmail;
    private String moreInfo;

    public Suppliers() {
    }

    public Suppliers(int supplierId, String name, String address, String phone, String emmail, String moreInfo) {
        this.supplierId = supplierId;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.emmail = emmail;
        this.moreInfo = moreInfo;
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

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }

    @Override
    public String toString() {
        return "Suppliers{" + "supplierId=" + supplierId + ", name=" + name + ", address=" + address + ", phone=" + phone + ", emmail=" + emmail + ", moreInfo=" + moreInfo + '}';
    }

    
    
}
