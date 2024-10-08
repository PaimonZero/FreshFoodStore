package model;

import java.util.Date;
import java.util.List;

public class ProductInfo {
   private int productId;
    private String productName;
    private String unitMeasure;
    private int unitPrice;
    private String categoryName;
    private String supplierName;
    private String supplierPhone;
    
    private List<BatchProduct> batchProducts;
    private List<Promos> promosList;

    public ProductInfo() {
    }

    public ProductInfo(int productId, String productName, String unitMeasure, int unitPrice, String categoryName, String supplierName, String supplierPhone, List<BatchProduct> batchProducts, List<Promos> promosList) {
        this.productId = productId;
        this.productName = productName;
        this.unitMeasure = unitMeasure;
        this.unitPrice = unitPrice;
        this.categoryName = categoryName;
        this.supplierName = supplierName;
        this.supplierPhone = supplierPhone;
        this.batchProducts = batchProducts;
        this.promosList = promosList;
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

    public String getUnitMeasure() {
        return unitMeasure;
    }

    public void setUnitMeasure(String unitMeasure) {
        this.unitMeasure = unitMeasure;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierPhone() {
        return supplierPhone;
    }

    public void setSupplierPhone(String supplierPhone) {
        this.supplierPhone = supplierPhone;
    }

    public List<BatchProduct> getBatchProducts() {
        return batchProducts;
    }

    public void setBatchProducts(List<BatchProduct> batchProducts) {
        this.batchProducts = batchProducts;
    }

    public List<Promos> getPromosList() {
        return promosList;
    }

    public void setPromosList(List<Promos> promosList) {
        this.promosList = promosList;
    }
    
    
}