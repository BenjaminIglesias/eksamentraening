/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.PhoneNumber;

/**
 *
 * @author Benjamin
 */
public class PhoneNumberDTO {
   private String number;
   private String description;

    public PhoneNumberDTO(PhoneNumber phoneNumber) {
        this.number = phoneNumber.getNumber();
        this.description = phoneNumber.getDescription();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
   
   
   
}
