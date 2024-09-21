/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author nguye
 */
public class InputDeteails {
        private Inputs inputId;
        private Product productId;
        private int quantity;

    public InputDeteails() {
    }

    public InputDeteails(Inputs inputId, Product productId, int quantity) {
        this.inputId = inputId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public Inputs getInputId() {
        return inputId;
    }

    public void setInputId(Inputs inputId) {
        this.inputId = inputId;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "InputDeteails{" + "inputId=" + inputId + ", productId=" + productId + ", quantity=" + quantity + '}';
    }
        
}
