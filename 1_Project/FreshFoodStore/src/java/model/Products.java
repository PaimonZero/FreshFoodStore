/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author nguye
 */
public class Products {

    private int productId;
    private String name;
    private String unitMeasure;
    private int supplierId;
    private int categoryId;
    private String description;
    private String image;
    private BigDecimal   unitPrice;
    private String status;
    private Date createdAt;
    private Date updateAt;
    
   

   

    public Products(int productId, String name, String unitMeasure, int supplierId, int categoryId, String description, String image, BigDecimal unitPrice, String status, Date createdAt, Date updateAt) {
        this.productId = productId;
        this.name = name;
        this.unitMeasure = unitMeasure;
        this.supplierId = supplierId;
        this.categoryId = categoryId;
        this.description = description;
        this.image = image;
        this.unitPrice = unitPrice;
        this.status = status;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
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

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        return "Products{" + "productId=" + productId + ", name=" + name + ", unitMeasure=" + unitMeasure + ", supplierId=" + supplierId + ", categoryId=" + categoryId + ", description=" + description + ", image=" + image + ", unitPrice=" + unitPrice + ", status=" + status + ", createdAt=" + createdAt + ", updateAt=" + updateAt + '}';
    }
    
    

    
}
