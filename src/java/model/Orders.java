/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author DELL
 */
public class Orders {
    private int orderId;
    private int userId;
    private double shippingFee;
    private boolean isConfirmed;
    private String paymentStatus;
    private String deliveryStatus;
    private String paymentType;
    private String deliveryLocation;
    private String receiverName;
    private String receiverPhone;
    private int shipperId;
    private Date orderCreatedAt;
    private Date orderCompletedAt;
    //thuộc tính không có trong bảng order
    private double total;
    private int quantity;
    private String totalString;
    private String orderCreatedAtString;
    private String orderCompletedAtString;
    public Orders() {
    }

    public Orders(int orderId, int userId, double shippingFee, boolean isConfirmed, String paymentStatus, String deliveryStatus, String paymentType, String deliveryLocation, String receiverName, String receiverPhone, int shipperId, Date orderCreatedAt, Date orderCompletedAt) {
        this.orderId = orderId;
        this.userId = userId;
        this.shippingFee = shippingFee;
        this.isConfirmed = isConfirmed;
        this.paymentStatus = paymentStatus;
        this.deliveryStatus = deliveryStatus;
        this.paymentType = paymentType;
        this.deliveryLocation = deliveryLocation;
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.shipperId = shipperId;
        this.orderCreatedAt = orderCreatedAt;
        this.orderCompletedAt = orderCompletedAt;
    }

    public Orders(int orderId, int userId, double shippingFee, boolean isConfirmed, String paymentStatus, String deliveryStatus, String paymentType, String deliveryLocation, String receiverName, String receiverPhone, int shipperId, Date orderCreatedAt, Date orderCompletedAt, double total, int quantity) {
        this.orderId = orderId;
        this.userId = userId;
        this.shippingFee = shippingFee;
        this.isConfirmed = isConfirmed;
        this.paymentStatus = paymentStatus;
        this.deliveryStatus = deliveryStatus;
        this.paymentType = paymentType;
        this.deliveryLocation = deliveryLocation;
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.shipperId = shipperId;
        this.orderCreatedAt = orderCreatedAt;
        this.orderCompletedAt = orderCompletedAt;
        this.total = total;
        this.quantity = quantity;
    }
    

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(double shippingFee) {
        this.shippingFee = shippingFee;
    }

    public boolean isIsConfirmed() {
        return isConfirmed;
    }

    public void setIsConfirmed(boolean isConfirmed) {
        this.isConfirmed = isConfirmed;
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

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getDeliveryLocation() {
        return deliveryLocation;
    }

    public void setDeliveryLocation(String deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public int getShipperId() {
        return shipperId;
    }

    public void setShipperId(int shipperId) {
        this.shipperId = shipperId;
    }

    public Date getOrderCreatedAt() {
        return orderCreatedAt;
    }

    public void setOrderCreatedAt(Date orderCreatedAt) {
        this.orderCreatedAt = orderCreatedAt;
    }

    public Date getOrderCompletedAt() {
        return orderCompletedAt;
    }

    public void setOrderCompletedAt(Date orderCompletedAt) {
        this.orderCompletedAt = orderCompletedAt;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTotalString() {
        return totalString;
    }

    public void setTotalString(String totalString) {
        this.totalString = totalString;
    }

    public String getOrderCreatedAtString() {
        return orderCreatedAtString;
    }

    public void setOrderCreatedAtString(String orderCreatedAtString) {
        this.orderCreatedAtString = orderCreatedAtString;
    }

    public String getOrderCompletedAtString() {
        return orderCompletedAtString;
    }

    public void setOrderCompletedAtString(String orderCompletedAtString) {
        this.orderCompletedAtString = orderCompletedAtString;
    }

//    @Override
//    public String toString() {
//        return "Orders{" + "orderId=" + orderId + ", userId=" + userId + ", shippingFee=" + shippingFee + ", isConfirmed=" + isConfirmed + ", paymentStatus=" + paymentStatus + ", deliveryStatus=" + deliveryStatus + ", paymentType=" + paymentType + ", deliveryLocation=" + deliveryLocation + ", receiverName=" + receiverName + ", receiverPhone=" + receiverPhone + ", shipperId=" + shipperId + ", orderCreatedAt=" + orderCreatedAt + ", orderCompletedAt=" + orderCompletedAt + ", total=" + total + ", quantity=" + quantity + '}';
//    }

    @Override
    public String toString() {
        return "Orders{" + "orderId=" + orderId + ", userId=" + userId + ", shippingFee=" + shippingFee + ", isConfirmed=" + isConfirmed + ", paymentStatus=" + paymentStatus + ", deliveryStatus=" + deliveryStatus + ", paymentType=" + paymentType + ", deliveryLocation=" + deliveryLocation + ", receiverName=" + receiverName + ", receiverPhone=" + receiverPhone + ", shipperId=" + shipperId + ", orderCreatedAt=" + orderCreatedAt + ", orderCompletedAt=" + orderCompletedAt + ", total=" + total + ", quantity=" + quantity + ", totalString=" + totalString + '}';
    }

   
    
}
