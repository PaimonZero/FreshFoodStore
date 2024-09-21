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
public class Promos {
    private int promotionId;
    private Product productId;
    private BigDecimal discount;

    public Promos() {
    }

    public Promos(int promotionId, Product productId, BigDecimal discount) {
        this.promotionId = promotionId;
        this.productId = productId;
        this.discount = discount;
    }

    public int getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(int promotionId) {
        this.promotionId = promotionId;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Promos{" + "promotionId=" + promotionId + ", productId=" + productId + ", discount=" + discount + '}';
    }
    
    
    
}
