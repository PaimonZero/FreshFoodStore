
package dto;

import java.util.Date;

public class ReceiptDTO {
    private int receiptId;
    private String supplierName;
    private Date inputDate;
    private double totalPrice;
    private int productTypes;        //Đếm số loại sản phẩm (số sản phẩm khác nhau)
    private int totalQuantity;          //tổng số lượng sản phẩm nhập
    private int supplierId;

    public ReceiptDTO() {
    }

    public ReceiptDTO(int receiptId, String supplierName, Date inputDate, double totalPrice, int numProductTypes, int totalQuantity) {
        this.receiptId = receiptId;
        this.supplierName = supplierName;
        this.inputDate = inputDate;
        this.totalPrice = totalPrice;
        this.productTypes = numProductTypes;
        this.totalQuantity = totalQuantity;
    }

    public ReceiptDTO(int receiptId, String supplierName, Date inputDate, double totalPrice, int productTypes, int totalQuantity, int supplierId) {
        this.receiptId = receiptId;
        this.supplierName = supplierName;
        this.inputDate = inputDate;
        this.totalPrice = totalPrice;
        this.productTypes = productTypes;
        this.totalQuantity = totalQuantity;
        this.supplierId = supplierId;
    }
    
    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }
    
    public int getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(int receiptId) {
        this.receiptId = receiptId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Date getInputDate() {
        return inputDate;
    }

    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getProductTypes() {
        return productTypes;
    }

    public void setProductTypes(int numProductTypes) {
        this.productTypes = numProductTypes;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    @Override
    public String toString() {
        return "ReceiptDTO{" + "receiptId=" + receiptId + ", supplierName=" + supplierName + ", inputDate=" + inputDate + ", totalPrice=" + totalPrice + ", productTypes=" + productTypes + ", totalQuantity=" + totalQuantity + '}';
    }
    
}
