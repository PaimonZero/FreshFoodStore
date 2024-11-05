
package model;

import java.util.Date;

public class ReceiptDetails {
    private int receiptDetailId;
    private int receiptId;
    private int productId;
    private int quantity;
    private double inputPrice;
    private Date expiryDate;

    public ReceiptDetails() {
    }

    public ReceiptDetails(int receiptDetailId, int receiptId, int productId, int quantity, double inputPrice, Date expiryDate) {
        this.receiptDetailId = receiptDetailId;
        this.receiptId = receiptId;
        this.productId = productId;
        this.quantity = quantity;
        this.inputPrice = inputPrice;
        this.expiryDate = expiryDate;
    }

    public ReceiptDetails(int receiptId, int productId, int quantity, double inputPrice, Date expiryDate) {
        this.receiptId = receiptId;
        this.productId = productId;
        this.quantity = quantity;
        this.inputPrice = inputPrice;
        this.expiryDate = expiryDate;
    }
    
    public int getReceiptDetailId() {
        return receiptDetailId;
    }

    public void setReceiptDetailId(int receiptDetailId) {
        this.receiptDetailId = receiptDetailId;
    }

    public int getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(int receiptId) {
        this.receiptId = receiptId;
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

    @Override
    public String toString() {
        return "ReceiptDetails{" + "receiptDetailId=" + receiptDetailId + ", receiptId=" + receiptId + ", productId=" + productId + ", quantity=" + quantity + ", inputPrice=" + inputPrice + ", expiryDate=" + expiryDate + '}';
    }
    
}
