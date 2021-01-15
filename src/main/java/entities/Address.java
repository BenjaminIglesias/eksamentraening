/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Benjamin
 */
@Entity
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
   
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    private String street;
    
    private String city;
    
    @OneToOne(mappedBy = "address")
    private Person person;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
         if(person != null){
        person.setAddress(this);
        }
    }
    
    public Address() {
    }

    public Address(String street, String city) {
        this.street = street;
        this.city = city;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
         if(person.getId() != null){
        person.setId(this.person.getId());
        }
    }

}
