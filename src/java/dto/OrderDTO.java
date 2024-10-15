package dto;

import java.sql.Timestamp;
import java.util.Date;

public class OrderDTO {
    private int orderId;
    private String paymentStatus;
    private String deliveryStatus;
    private String receiverName;
    private String shipperName;
    private Timestamp orderCreatedAt;

    private double totalPrice;

    // thuong
    private  int userId;
    private double shippingFee;
    private int isConfirmed;
    private String paymentType;
    private String deliveryLocation;
    private String receiverPhone;
    private int shipperId;
    private Date orderCreated_At;
        private Date orderComplete_At;

        
        
        

     public OrderDTO(int orderId, int userId, double shippingFee, int isConfirmed, String paymentStatus, String deliveryStatus, String paymentType, String deliveryLocation, String receiverName, String receiverPhone, int shipperId, Date orderCreatedAt, Date orderCompletedAt) {
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
        this.orderCreated_At = orderCreated_At;
        this.orderComplete_At = orderComplete_At;
    }
        
        
        
        
        
        
        
        
        
    public OrderDTO() {
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

    public int getIsConfirmed() {
        return isConfirmed;
    }

    public void setIsConfirmed(int isConfirmed) {
        this.isConfirmed = isConfirmed;
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

    public Date getOrderCreated_At() {
        return orderCreated_At;
    }

    public void setOrderCreated_At(Date orderCreated_At) {
        this.orderCreated_At = orderCreated_At;
    }

    public Date getOrderComplete_At() {
        return orderComplete_At;
    }

    public void setOrderComplete_At(Date orderComplete_At) {
        this.orderComplete_At = orderComplete_At;
    }

    
    
    
    

    public OrderDTO(int orderId, String customerName, String paymentStatus, double totalPrice, String orderDate, String shipperName, String deliveryStatus) {
        this.orderId = orderId;
        this.receiverName = customerName;  // Sử dụng receiverName để khớp với customerName
        this.paymentStatus = paymentStatus;
        this.totalPrice = totalPrice;
        this.orderCreatedAt = Timestamp.valueOf(orderDate); // Chuyển từ chuỗi thành Timestamp
        this.shipperName = shipperName;
        this.deliveryStatus = deliveryStatus;
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

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getShipperName() {
        return shipperName;
    }

    public void setShipperName(String shipperName) {
        this.shipperName = shipperName;
    }

    public Timestamp getOrderCreatedAt() {
        return orderCreatedAt;
    }

    public void setOrderCreatedAt(Timestamp orderCreatedAt) {
        this.orderCreatedAt = orderCreatedAt;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "OrderDTO{" + "orderId=" + orderId + ", paymentStatus=" + paymentStatus + ", deliveryStatus=" + deliveryStatus + ", receiverName=" + receiverName + ", shipperName=" + shipperName + ", orderCreatedAt=" + orderCreatedAt + ", totalPrice=" + totalPrice + ", userId=" + userId + ", shippingFee=" + shippingFee + ", isConfirmed=" + isConfirmed + ", paymentType=" + paymentType + ", deliveryLocation=" + deliveryLocation + ", receiverPhone=" + receiverPhone + ", shipperId=" + shipperId + ", orderCreated_At=" + orderCreated_At + ", orderComplete_At=" + orderComplete_At + '}';
    }

   
}