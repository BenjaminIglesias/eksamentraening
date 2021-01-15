/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.PersonDTO;
import entities.Address;
import entities.Person;
import java.util.List;

/**
 *
 * @author Benjamin
 */
public interface IPersonFacade {
  //get 
  public long getCount();
  public List<PersonDTO> getUsers();  
  public PersonDTO getUserByID(int id);
  //post
  public PersonDTO createUser(Person p);
  //put 
    public String editUserByID(PersonDTO p);
  //delete
  public PersonDTO deleteUserByID(Long id);

  public void fillUsersInDB();
}
