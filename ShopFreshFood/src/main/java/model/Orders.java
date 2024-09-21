/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author nguye
 */
public class Orders {
    private int orderId;
    private Date dateOutput;
    private String status;

    public Orders() {
    }

    public Orders(int orderId, Date dateOutput, String status) {
        this.orderId = orderId;
        this.dateOutput = dateOutput;
        this.status = status;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getDateOutput() {
        return dateOutput;
    }

    public void setDateOutput(Date dateOutput) {
        this.dateOutput = dateOutput;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Orders{" + "orderId=" + orderId + ", dateOutput=" + dateOutput + ", status=" + status + '}';
    }
    
    
    
}
