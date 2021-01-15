/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Book;
import entities.Person;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Benjamin
 */
public class BookDTO {
    String title;
    String ISBN;
    List<BookAuthorDTO> authors = new ArrayList<>();
    
    public BookDTO(Book book) {
        this.title = book.getTitle() ;
        this.ISBN = book.getISBN();
        book.getAuthors().forEach((n) -> authors.add(new BookAuthorDTO(n)));
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    
}
