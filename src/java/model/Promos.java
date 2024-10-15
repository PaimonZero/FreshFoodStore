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
public class Promos {
    private int promotionId;
    private int productId;
    private int quantitySale;
    private double discount;
    private Date startDate;
    private Date endDate;

    public Promos() {
    }

    public Promos(int promotionId, int productId, int quantitySale, double discount, Date startDate, Date endDate) {
        this.promotionId = promotionId;
        this.productId = productId;
        this.quantitySale = quantitySale;
        this.discount = discount;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Promos{" + "promotionId=" + promotionId + ", productId=" + productId + ", quantitySale=" + quantitySale + ", discount=" + discount + ", startDate=" + startDate + ", endDate=" + endDate + '}';
    }

    public int getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(int promotionId) {
        this.promotionId = promotionId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantitySale() {
        return quantitySale;
    }

    public void setQuantitySale(int quantitySale) {
        this.quantitySale = quantitySale;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    
    
}
