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
public class BashesProduct {
    private int batchId;
    private Product productId;
    private int quantity;
    private Date expiryDate;

    public BashesProduct() {
    }

    public BashesProduct(int batchId, Product productId, int quantity, Date expiryDate) {
        this.batchId = batchId;
        this.productId = productId;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
    }

    public int getBatchId() {
        return batchId;
    }

    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public String toString() {
        return "BashesProduct{" + "batchId=" + batchId + ", productId=" + productId + ", quantity=" + quantity + ", expiryDate=" + expiryDate + '}';
    }
    
    
}
