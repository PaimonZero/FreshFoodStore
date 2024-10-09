/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author nguye
 */
public class ProductDTO {
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
    private String nameCategory;
    // promos
     private int promotionId;
    private int quantitySale;
    private Double discount;
    private Date startDate;
    private Date endDate;

    public ProductDTO(int productId, String name, String unitMeasure, int supplierId, int categoryId, String description, String image, BigDecimal unitPrice, String status, Date createdAt, Date updatedAt, int promotionId, int quantitySale, double discount, Date startDate, Date endDate) {
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
    this.updateAt = updatedAt;
    this.promotionId = promotionId;
    this.quantitySale = quantitySale;
    this.discount = discount;
    this.startDate = startDate;
    this.endDate = endDate;
    }

    public ProductDTO(int productId, String name, String unitMeasure, int supplierId, int categoryId, String description, String image, BigDecimal unitPrice, String status, Date createdAt, Date updateAt, String nameCategory,Double discount) {
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
        this.nameCategory=nameCategory;
        this.discount=discount;
    }
    // Top 4 san pham noi bat
     public ProductDTO(int productId, String name, BigDecimal unitPrice, String image, Double discount) {
        this.productId = productId;
        this.name =name;
        this.unitPrice = unitPrice;
        this.image = image;
        this.discount=discount;
    }
     
      public ProductDTO(int productId, String name, String unitMeasure, int supplierId, int categoryId, String description, String image, BigDecimal unitPrice, String status, Date createdAt, Date updateAt, Double discount) {
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
        this.discount = discount;
    }

       public ProductDTO(int productId, String name, String unitMeasure, int supplierId, int categoryId, String description, String image, BigDecimal unitPrice, String status, Date createdAt, Date updateAt) {
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

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public int getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(int promotionId) {
        this.promotionId = promotionId;
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

    @Override
    public String toString() {
        return "ProductDTO{" + "productId=" + productId + ", name=" + name + ", unitMeasure=" + unitMeasure + ", supplierId=" + supplierId + ", categoryId=" + categoryId + ", description=" + description + ", image=" + image + ", unitPrice=" + unitPrice + ", status=" + status + ", createdAt=" + createdAt + ", updateAt=" + updateAt + ", nameCategory=" + nameCategory + ", promotionId=" + promotionId + ", quantitySale=" + quantitySale + ", discount=" + discount + ", startDate=" + startDate + ", endDate=" + endDate + '}';
    }
    

    
    
    
}
