
package dto;

import java.util.Date;

public class ReceiptDetailDTO {
    private int receiptDetailId;
    private int productId;
    private String productName;
    private int quantity;
    private double inputPrice;
    private Date expiryDate;
    private int batchId;

    public ReceiptDetailDTO() {
    }

    public ReceiptDetailDTO(int receiptDetailId, int productId, String productName, int quantity, double inputPrice, Date expiryDate, int batchId) {
        this.receiptDetailId = receiptDetailId;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.inputPrice = inputPrice;
        this.expiryDate = expiryDate;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getInputPrice() {
        return inputPrice;
    }

    public void setInputPrice(double inputPrice) {
        this.inputPrice = inputPrice;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getBatchId() {
        return batchId;
    }

    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }

    @Override
    public String toString() {
        return "ReceiptDetailDTO{" + "receiptDetailId=" + receiptDetailId + ", productId=" + productId + ", productName=" + productName + ", quantity=" + quantity + ", inputPrice=" + inputPrice + ", expiryDate=" + expiryDate + ", batchId=" + batchId + '}';
    }
    
}
