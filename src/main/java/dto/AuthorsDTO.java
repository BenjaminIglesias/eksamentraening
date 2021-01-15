/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Person;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Benjamin
 */
public class AuthorsDTO {
           List<PersonDTO> allPersons = new ArrayList();
        
        public AuthorsDTO(List<Person> authorEntities) {
        authorEntities.forEach((p) -> {
            allPersons.add(new PersonDTO(p));
        });

}

    public List<PersonDTO> getAll() {
        return allPersons;
    }  
}
