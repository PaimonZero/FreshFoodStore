/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;

/**
 *
 * @author nguye
 */
public class OrderDetail {
        private int orderDetailId;
        private Orders orderId;
        private BashesProduct bashesProductId;
        private BigDecimal unitPriceOut;
        private int quantity;

    public OrderDetail() {
    }

    public OrderDetail(int orderDetailId, Orders orderId, BashesProduct bashesProductId, BigDecimal unitPriceOut, int quantity) {
        this.orderDetailId = orderDetailId;
        this.orderId = orderId;
        this.bashesProductId = bashesProductId;
        this.unitPriceOut = unitPriceOut;
        this.quantity = quantity;
    }

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public BashesProduct getBashesProductId() {
        return bashesProductId;
    }

    public void setBashesProductId(BashesProduct bashesProductId) {
        this.bashesProductId = bashesProductId;
    }

    public BigDecimal getUnitPriceOut() {
        return unitPriceOut;
    }

    public void setUnitPriceOut(BigDecimal unitPriceOut) {
        this.unitPriceOut = unitPriceOut;
    }

    

    public Orders getOrderId() {
        return orderId;
    }

    public void setOrderId(Orders orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderDetail{" + "orderDetailId=" + orderDetailId + ", orderId=" + orderId + ", bashesProductId=" + bashesProductId + ", unitPriceOut=" + unitPriceOut + ", quantity=" + quantity + '}';
    }

    
        
        
        
}
