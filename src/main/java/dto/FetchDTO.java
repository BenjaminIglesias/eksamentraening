/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author Benjamin
 */
public class FetchDTO {
  
    private long id;
    private String title;
    public FetchDTO(FetchDTO fetchdto) {
        this.id = fetchdto.getId();
        this.title = fetchdto.getTitle();
       
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "FetchDTO{" + "id=" + id + ", title=" + title + '}';
    }

    
}
