package facades;

import dto.PersonDTO;
import entities.Person;
import utils.EMF_Creator;
import entities.RenameMe;
import errorhandling.API_Exception;
import static io.restassured.RestAssured.given;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
@Disabled
public class FacadeExampleTest {

    private static EntityManagerFactory emf;
    private static FacadeExample facade;
    private static PersonFacade personFacade;
    private static Person p1, p2;

    public FacadeExampleTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = FacadeExample.getFacadeExample(emf);
       personFacade = PersonFacade.getPersonFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the code below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
           
              em.getTransaction().begin();
            //Delete existing users and roles to get a "fresh" database
            em.createQuery("delete from Person").executeUpdate();
            em.createQuery("delete from Address").executeUpdate();
            em.createQuery("delete from PhoneNumber").executeUpdate();
            em.createQuery("delete from Book").executeUpdate();
             em.getTransaction().commit();
           em.getTransaction().begin();
             p1 = new Person("person1","person1","person1");
           p2 = new Person("person2","person2","person2");
            em.persist(p1);
            em.persist(p2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    // TODO: Delete or change this metho
@Disabled
    @Test
    public void testAFacadeMethod() {
        assertEquals(2, personFacade.getCount(), "Expects two rows in the database");
    }
      @Test 
    public void testGetAllPersons() {
        List<Person> personsDtos;
        personsDtos = given()
                .contentType("application/json")
                .when()
                .get("/Person/getAll")
                .then()
                .extract().body().jsonPath().getList("getAll", Person.class);
        
        PersonDTO pDto1 = new PersonDTO(p1);
         PersonDTO pDto2 = new PersonDTO(p2);     
                
         // HUSK equals metode inde i PersonDTO for at det virker.
         
         assertThat(personsDtos, containsInAnyOrder(p1, p2));
    

}}
