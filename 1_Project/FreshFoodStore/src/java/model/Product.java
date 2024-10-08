/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author PC
 */
public class Product {
    private int productId;
    private String name, unitMeasure, status;
    private int unitPrice;
    private int quantity; 
    private int discount; 

    public Product() {
    }

    public Product(int productId, String name, String unitMeasure, String status, int unitPrice, int quantity, int discount) {
        this.productId = productId;
        this.name = name;
        this.unitMeasure = unitMeasure;
        this.status = status;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.discount = discount;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnitMeasure() {
        return unitMeasure;
    }

    public void setUnitMeasure(String unitMeasure) {
        this.unitMeasure = unitMeasure;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Product{" + "productId=" + productId + ", name=" + name + ", unitMeasure=" + unitMeasure + ", status=" + status + ", unitPrice=" + unitPrice + ", quantity=" + quantity + ", discount=" + discount + '}';
    }

    
    
}
