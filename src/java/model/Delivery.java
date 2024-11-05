/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;
import java.security.Timestamp;
import java.util.Date;

/**
 *
 * @author PC
 */
public class Delivery {
    private String userName, receiverPhone; 
    private int orderId;
    private String paymentStatus, deliveryStatus;
    private Date  orderDate;
    private BigDecimal totalValue;

    public Delivery() {
    }

    public Delivery(String userName, String receiverPhone, int orderId, String paymentStatus, String deliveryStatus, Date orderDate, BigDecimal totalValue) {
        this.userName = userName;
        this.receiverPhone = receiverPhone;
        this.orderId = orderId;
        this.paymentStatus = paymentStatus;
        this.deliveryStatus = deliveryStatus;
        this.orderDate = orderDate;
        this.totalValue = totalValue;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

   
}
