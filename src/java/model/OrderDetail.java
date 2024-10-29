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
public class OrderDetail {
    private int orderDetailId;
    private int orderId;
    private int batchId;
    private double unitPriceOut;
    private int quantity;
    private String productImage;//ko có trong bảng orderDetail
    private String productName;//ko có trong bảng orderDetail
    //thuộc tính không có trong DB
    private String unitPriceOutString;
    private String orderCreatedAtString;
    private String discountString;
    private String shippingFeeString;
    //thuộc tính không có trong bảng orderDetail
    private String fullName;
    private String address;
    private String email;
    private String phone;
    private String receiverName;
    private String deliveryLocation;
    private String receiverPhone;
    private double shippingFee;
    private String deliveryStatus;
    private String paymentType;
    private String paymentStatus;
    private Date orderCreatedAt;
    private double discount;
    //Nam thêm
    private int shipperId;
    private String shipperName;
    private String shipperPhone;
    
    public OrderDetail() {
    }

//    public OrderDetail(int orderDetailId, int orderId, int batchId, double unitPriceOut, int quantity, String productImage, String productName) {
//        this.orderDetailId = orderDetailId;
//        this.orderId = orderId;
//        this.batchId = batchId;
//        this.unitPriceOut = unitPriceOut;
//        this.quantity = quantity;
//        this.productImage = productImage;
//        this.productName = productName;
//    }

    public OrderDetail(int orderDetailId, int orderId, int batchId, double unitPriceOut, int quantity, 
            String productImage, String productName, String fullName, String address, String email, 
            String phone, String receiverName, String deliveryLocation, String receiverPhone, double shippingFee, 
            String deliveryStatus, String paymentType, Date orderCreatedAt, double discount) {
        this.orderDetailId = orderDetailId;
        this.orderId = orderId;
        this.batchId = batchId;
        this.unitPriceOut = unitPriceOut;
        this.quantity = quantity;
        this.productImage = productImage;
        this.productName = productName;
        this.fullName = fullName;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.receiverName = receiverName;
        this.deliveryLocation = deliveryLocation;
        this.receiverPhone = receiverPhone;
        this.shippingFee = shippingFee;
        this.deliveryStatus = deliveryStatus;
        this.paymentType = paymentType;
        this.orderCreatedAt = orderCreatedAt;
        this.discount=discount;
    }

    public int getShipperId() {
        return shipperId;
    }

    public void setShipperId(int shipperId) {
        this.shipperId = shipperId;
    }

    public String getShipperName() {
        return shipperName;
    }

    public void setShipperName(String shipperName) {
        this.shipperName = shipperName;
    }

    public String getShipperPhone() {
        return shipperPhone;
    }

    public void setShipperPhone(String shipperPhone) {
        this.shipperPhone = shipperPhone;
    }
    
    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getBatchId() {
        return batchId;
    }

    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }

    public double getUnitPriceOut() {
        return unitPriceOut;
    }

    public void setUnitPriceOut(double unitPriceOut) {
        this.unitPriceOut = unitPriceOut;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getUnitPriceOutString() {
        return unitPriceOutString;
    }

    public void setUnitPriceOutString(String unitPriceOutString) {
        this.unitPriceOutString = unitPriceOutString;
    }
    //thuộc tính không có trong bảng orderDetail

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
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

    public double getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(double shippingFee) {
        this.shippingFee = shippingFee;
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

    public Date getOrderCreatedAt() {
        return orderCreatedAt;
    }

    public void setOrderCreatedAt(Date orderCreatedAt) {
        this.orderCreatedAt = orderCreatedAt;
    }

    public String getOrderCreatedAtString() {
        return orderCreatedAtString;
    }

    public void setOrderCreatedAtString(String orderCreatedAtString) {
        this.orderCreatedAtString = orderCreatedAtString;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getDiscountString() {
        return discountString;
    }

    public void setDiscountString(String discountString) {
        this.discountString = discountString;
    }

    public String getShippingFeeString() {
        return shippingFeeString;
    }

    public void setShippingFeeString(String shippingFeeString) {
        this.shippingFeeString = shippingFeeString;
    }
    
    @Override
    public String toString() {
        return "OrderDetail{" + "orderDetailId=" + orderDetailId + ", orderId=" + orderId + ", batchId=" + batchId + ", unitPriceOut=" + unitPriceOut + ", quantity=" + quantity + ", productImage=" + productImage + ", productName=" + productName + ", unitPriceOutString=" + unitPriceOutString + ", orderCreatedAtString=" + orderCreatedAtString + ", fullName=" + fullName + ", address=" + address + ", email=" + email + ", phone=" + phone + ", receiverName=" + receiverName + ", deliveryLocation=" + deliveryLocation + ", receiverPhone=" + receiverPhone + ", shippingFee=" + shippingFee + ", deliveryStatus=" + deliveryStatus + ", paymentType=" + paymentType + ", orderCreatedAt=" + orderCreatedAt + ", discount=" + discount + '}';
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
    
    
}
