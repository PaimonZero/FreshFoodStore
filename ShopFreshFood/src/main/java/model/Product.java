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
public class Product {
    private int productId;
    private String name;
    private Units unitId;
        private Suppliers supplierId;
    private Category categoryId;
    private String description;
    private String image;
    private BigDecimal unitPrice;
    private String status;

    public Product() {
    }

    public Product(int productId, String name, Units unitId, Suppliers supplierId, Category categoryId, String description, String image, BigDecimal unitPrice, String status) {
        this.productId = productId;
        this.name = name;
        this.unitId = unitId;
        this.supplierId = supplierId;
        this.categoryId = categoryId;
        this.description = description;
        this.image = image;
        this.unitPrice = unitPrice;
        this.status = status;
    }

    public Units getUnitId() {
        return unitId;
    }

    public void setUnitId(Units unitId) {
        this.unitId = unitId;
    }

    public Suppliers getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Suppliers supplierId) {
        this.supplierId = supplierId;
    }

    public Category getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Category categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Product{" + "productId=" + productId + ", name=" + name + ", unitId=" + unitId + ", supplierId=" + supplierId + ", categoryId=" + categoryId + ", description=" + description + ", image=" + image + ", unitPrice=" + unitPrice + ", status=" + status + '}';
    }

    
    
    
    
}
