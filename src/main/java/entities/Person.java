/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.TableGenerator;

/**
 *
 * @author Benjamin
 */
@Entity
@NamedQuery(name = "Person.deleteAllRows", query = "DELETE from Person")
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String birthyear;

    @OneToOne(cascade = CascadeType.ALL)
    
    private Address address;
    
    
    @OneToMany(mappedBy = "person",cascade = CascadeType.ALL)
    private List<PhoneNumber> phoneNumbers;
    
    @ManyToMany(mappedBy = "authors" , cascade = CascadeType.ALL)
    private List<Book> books;
    
    
    public Person() {
    }
public Person(String firstName, String lastName, String birthyear) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthyear = birthyear;
        this.address = address;
        this.phoneNumbers = new ArrayList<>();
        this.books = new ArrayList<>();
    }
    public Person(String firstName, String lastName, String birthyear, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthyear = birthyear;
        this.address = address;
        this.phoneNumbers = new ArrayList<>();
        this.books = new ArrayList<>();
    }
    
    public void addPhoneNumber(PhoneNumber phoneNumber){
    this.phoneNumbers.add(phoneNumber);
    if (phoneNumber != null){
    phoneNumber.setPerson(this);
    }
    }
    
    public void addBook(Book book){
    if (book != null){
    this.books.add(book);
    book.getAuthors().add(this);
    }
    }

    public void removeBook(Book book){
    if(book != null){
      books.remove(book);
      book.getAuthors().remove(this);
    }
    }
    
    
    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

  
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
        if(address != null){
        address.setPerson(this);
        }
    }

    public List<Book> getBooks() {
        return books;
    }

  

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", birthyear=" + birthyear + ", address=" + address + '}';
    }

    
}
