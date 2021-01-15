/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Book;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Benjamin
 */
public class BooksDTO {
         List<BookDTO> allBooks = new ArrayList();
        
        public BooksDTO(List<Book> bookEntities) {
        bookEntities.forEach((p) -> {
            allBooks.add(new BookDTO(p));
        });

}

    public List<BookDTO> getAll() {
        return allBooks;
    }
}
