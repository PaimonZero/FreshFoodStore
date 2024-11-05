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
public class OrderDetailDTO {

    private int orderDetailId;
    private int orderId;
    private int batchId;
    private BigDecimal unitPriceOut;
    private int quantity;
    // product
    private int productId;
    private String name;
    private String unitMeasure;
    private int supplierId;
    private int categoryId;
    private String description;
    private String image;
    private BigDecimal unitPrice;
    private String status;
    private Date createdAt;
    private Date updateAt;

    //promos
    private int promotionId;
    private int quantitySale;
    private double discount;
    private Date startDate;
    private Date endDate;
    private String UnitPriceString;
    private String discountString;
    private int batchQuantity;
    private double shippingFee;
    private String shippingFeeString;
    public OrderDetailDTO() {
    }

    public OrderDetailDTO(int orderDetailId, int orderId, int batchId, BigDecimal unitPriceOut, int quantity) {
        this.orderDetailId = orderDetailId;
        this.orderId = orderId;
        this.batchId = batchId;
        this.unitPriceOut = unitPriceOut;
        this.quantity = quantity;
    }
    public OrderDetailDTO(int orderDetailId, int orderId, int batchId, BigDecimal unitPriceOut, int quantity, int productId, String name, String unitMeasure, int supplierId, int categoryId, String description, String image, BigDecimal unitPrice, String status, Date createdAt, Date updateAt, int promotionId, int quantitySale, double discount, Date startDate, Date endDate, int batchQuantity, double shippingFee) {
        this.orderDetailId = orderDetailId;
        this.orderId = orderId;
        this.batchId = batchId;
        this.unitPriceOut = unitPriceOut;
        this.quantity = quantity;
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
        this.promotionId = promotionId;
        this.quantitySale = quantitySale;
        this.discount = discount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.batchQuantity = batchQuantity;
        this.shippingFee = shippingFee;
    }
    public OrderDetailDTO(int orderDetailId, int orderId, int batchId, BigDecimal unitPriceOut, int quantity, int productId, String name, String unitMeasure, int supplierId, int categoryId, String description, String image, BigDecimal unitPrice, String status, Date createdAt, Date updateAt, int promotionId, int quantitySale, double discount, Date startDate, Date endDate, int batchQuantity) {
        this.orderDetailId = orderDetailId;
        this.orderId = orderId;
        this.batchId = batchId;
        this.unitPriceOut = unitPriceOut;
        this.quantity = quantity;
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
        this.promotionId = promotionId;
        this.quantitySale = quantitySale;
        this.discount = discount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.batchQuantity = batchQuantity;

    }
    
    

    public int getBatchQuantity() {
        return batchQuantity;
    }

    public void setBatchQuantity(int batchQuantity) {
        this.batchQuantity = batchQuantity;
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

    public BigDecimal getUnitPriceOut() {
        return unitPriceOut;
    }

    public void setUnitPriceOut(BigDecimal unitPriceOut) {
        this.unitPriceOut = unitPriceOut;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public String getUnitPriceString() {
        return UnitPriceString;
    }

    public void setUnitPriceString(String UnitPriceString) {
        this.UnitPriceString = UnitPriceString;
    }

    public String getDiscountString() {
        return discountString;
    }

    public void setDiscountString(String discountString) {
        this.discountString = discountString;
    }

    public double getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(double shippingFee) {
        this.shippingFee = shippingFee;
    }

    public String getShippingFeeString() {
        return shippingFeeString;
    }

    public void setShippingFeeString(String shippingFeeString) {
        this.shippingFeeString = shippingFeeString;
    }

    @Override
    public String toString() {
        return "OrderDetailDTO{" + "orderDetailId=" + orderDetailId + ", orderId=" + orderId + ", batchId=" + batchId + ", unitPriceOut=" + unitPriceOut + ", quantity=" + quantity + ", productId=" + productId + ", name=" + name + ", unitMeasure=" + unitMeasure + ", supplierId=" + supplierId + ", categoryId=" + categoryId + ", description=" + description + ", image=" + image + ", unitPrice=" + unitPrice + ", status=" + status + ", createdAt=" + createdAt + ", updateAt=" + updateAt + ", promotionId=" + promotionId + ", quantitySale=" + quantitySale + ", discount=" + discount + ", startDate=" + startDate + ", endDate=" + endDate + ", UnitPriceString=" + UnitPriceString + ", discountString=" + discountString + ", batchQuantity=" + batchQuantity + ", shippingFee=" + shippingFee + ", shippingFeeString=" + shippingFeeString + '}';
    }
    
}
//done