/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author nguye
 */
public class Inputs {
    private int inputId ;
    private Date dateInput;

    public Inputs() {
    }

    public Inputs(int inputId, Date dateInput) {
        this.inputId = inputId;
        this.dateInput = dateInput;
    }

    public int getInputId() {
        return inputId;
    }

    public void setInputId(int inputId) {
        this.inputId = inputId;
    }

    public Date getDateInput() {
        return dateInput;
    }

    public void setDateInput(Date dateInput) {
        this.dateInput = dateInput;
    }

    @Override
    public String toString() {
        return "Inputs{" + "inputId=" + inputId + ", dateInput=" + dateInput + '}';
    }
    
}
