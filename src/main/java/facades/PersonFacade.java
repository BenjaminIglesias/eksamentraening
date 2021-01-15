/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.PersonDTO;
import dto.PersonsDTO;
import entities.Address;
import entities.Book;

import entities.Person;
import entities.PhoneNumber;
import entities.Role;
import entities.Tester;
import entities.User;
import errorhandling.ErrorRetrieving;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;

/**
 *
 * @author Benjamin
 */
public class PersonFacade implements IPersonFacade{
    private static EntityManagerFactory emf;
      private static PersonFacade instance;
      private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public PersonFacade() {
    }
      
        public static PersonFacade getPersonFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    
    @Override
    public List<PersonDTO> getUsers(){
        EntityManager em = getEntityManager();
          try {
        List<Person> Users = em.createQuery("Select c from Person c" , Person.class).getResultList();
        return new PersonsDTO(Users).getAll();
          } 
          finally{
          em.close();
          }
    
    }
    
    @Override
    public PersonDTO getUserByID(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String editUserByID(PersonDTO p) {
     return null;    
    }

    @Override
    public PersonDTO deleteUserByID(Long id) {
                  EntityManager em = getEntityManager();
        Person person = em.find(Person.class, id);

        
    em.getTransaction().begin();
    em.remove(person);
    em.getTransaction().commit();
  
  return new PersonDTO(person);
    }

    @Override
    public PersonDTO createUser(Person p) {
         EntityManager em = getEntityManager();
         em.getTransaction().begin();
         
         em.persist(p);
         
         em.getTransaction().commit();
        return new PersonDTO(p);



    }

    @Override
    public long getCount() {
        EntityManager em = emf.createEntityManager();
        try{
            long count = (long)em.createQuery("SELECT COUNT(r) FROM Person r").getSingleResult();
            return count;
        }finally{  
            em.close();
        }
}

    @Override
    public void fillUsersInDB() {
    
    EntityManager em = emf.createEntityManager();
    

        
    User user = new User("bobby", "bobby");
    User admin = new User("admin", "test1");
    User both = new User("user_admin", "test1");
    PhoneNumber ph1 = new PhoneNumber("123","hjem");
    PhoneNumber ph2 = new PhoneNumber("12345","hjem");
    PhoneNumber ph3 = new PhoneNumber("1243","arbejde");
    Book b1 = new Book("Harry Potter", "123");
      Book b2 = new Book("Bog 2", "1234");
        Book b3 = new Book("John Bogen", "1234");
    Address a1 = new Address("Vej","By");
        Address a2 = new Address("Veje","Bye");
    Person p1 = new Person("Hans","Larsen","1963",a1);
    Person p2 = new Person("Kristine","Eriksen","1978",a2);
    p1.addPhoneNumber(ph1);
    p1.addPhoneNumber(ph2);
    p2.addPhoneNumber(ph3);
    p1.addBook(b1);
    p1.addBook(b3);
    p2.addBook(b2);
    p2.addBook(b1);
   
    if(admin.getUserPass().equals("test")||user.getUserPass().equals("test")||both.getUserPass().equals("test"))
      throw new UnsupportedOperationException("You have not changed the passwords");

    em.getTransaction().begin();
    Role userRole = new Role("user");
    Role adminRole = new Role("admin");
    user.addRole(userRole);
    admin.addRole(adminRole);
    both.addRole(userRole);
    both.addRole(adminRole);
    em.persist(userRole);
    em.persist(adminRole);
    em.persist(user);
    em.persist(admin);
    em.persist(both);
    em.persist(p1);
    em.persist(p2);
    em.getTransaction().commit();
    System.out.println("PW: " + user.getUserPass());
    System.out.println("Testing user with OK password: " + user.verifyPassword("test"));
    System.out.println("Testing user with wrong password: " + user.verifyPassword("test1"));
    System.out.println("Created TEST Users");
   

    }
}