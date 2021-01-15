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
public class PersonDTO {
    String firstName;
    String lastName;
    String birthyear;
    AddressDTO address;
    PhoneNumbersDTO phoneNumbers;
    BooksDTO books;
    
    
    public PersonDTO(Person p) {
        this.firstName = p.getFirstName();
        this.lastName = p.getLastName();
        this.birthyear = p.getBirthyear();
        this.address = new AddressDTO(p.getAddress().getStreet() , p.getAddress().getCity());
        this.phoneNumbers = new PhoneNumbersDTO(p.getPhoneNumbers());
        this.books = new BooksDTO(p.getBooks());
    }

    public PersonDTO(String firstName, String lastName, String birthyear) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthyear = birthyear;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public PhoneNumbersDTO getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(PhoneNumbersDTO phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public BooksDTO getBooks() {
        return books;
    }

    public void setBooks(BooksDTO books) {
        this.books = books;
    }
    

   

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthyear() {
        return birthyear;
    }

    public void setBirthyear(String birthyear) {
        this.birthyear = birthyear;
    }

    
    
    
}
