
package dto;

import java.sql.Timestamp;

public class OrderDTO {
    private int orderId;
    private String paymentStatus;
    private String deliveryStatus;
    private String receiverName;
    private String shipperName;
    private Timestamp orderCreatedAt;

    private double totalPrice;

    public OrderDTO() {
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
        return "OrderDisplay{" + "orderId=" + orderId + ", receiverName=" + receiverName + ", paymentStatus=" + paymentStatus + ", totalPrice=" + totalPrice + ", orderCreatedAt=" + orderCreatedAt + ", shipperName=" + shipperName +  ", deliveryStatus=" + deliveryStatus + '}';
    }
}
