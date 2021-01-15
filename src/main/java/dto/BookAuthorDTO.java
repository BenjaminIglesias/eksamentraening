/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Person;

/**
 *
 * @author Benjamin
 */
public class BookAuthorDTO {
    
   String name;

    public BookAuthorDTO(Person p) {
        this.name = p.getFirstName() + " " + p.getLastName();
    }
   
   
    
}
