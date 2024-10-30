
package model;

import java.util.Date;

public class BatchesProduct {
    private int batchId;
    private int receiptDetailId;
    private int productId;
    private int quantity;
    private Date expiryDate;
    private String productName;
    private double unitPrice;
    
    public BatchesProduct() {
    }

    public BatchesProduct(int batchId, int receiptDetailId, int productId, int quantity, Date expiryDate, String productName, double unitPrice) {
        this.batchId = batchId;
        this.receiptDetailId = receiptDetailId;
        this.productId = productId;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
        this.productName = productName;
        this.unitPrice = unitPrice;
    }
    
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getBatchId() {
        return batchId;
    }

    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }

    public int getReceiptDetailId() {
        return receiptDetailId;
    }

    public void setReceiptDetailId(int receiptDetailId) {
        this.receiptDetailId = receiptDetailId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
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
        return "BatchesProduct{" + "batchId=" + batchId + ", receiptDetailId=" + receiptDetailId + ", productId=" + productId + ", quantity=" + quantity + ", expiryDate=" + expiryDate + '}';
    }
    
}
