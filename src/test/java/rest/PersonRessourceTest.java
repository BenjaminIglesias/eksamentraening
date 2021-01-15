package rest;

import entities.Address;
import entities.Person;
import entities.PhoneNumber;
import entities.RenameMe;
import entities.Role;
import entities.User;
import utils.EMF_Creator;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
//Uncomment the line below, to temporarily disable this test
//@Disabled

public class PersonRessourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/jpareststarter/api";
    private static Person p1;
    private static Address a1;

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;
    private static String payload = "{\n" +
        "  \"firstName\": \"firstName\",\n" +
        "  \"lastName\": \"lastName\",\n" +
        "  \"birthyear\": \"1990\"\n" +
        "}";
    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        //System.in.read();

        //Don't forget this, if you called its counterpart in @BeforeAll
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }

    // Setup the DataBase (used by the test-server and this test) in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the EntityClass used below to use YOUR OWN (renamed) Entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        a1 = new Address("Vej","By");
        PhoneNumber ph1 = new PhoneNumber("123","hjem");
        
        p1 = new Person("Hans","Hansen","1995",a1);
        p1.addPhoneNumber(ph1);
        
       
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
           
            em.getTransaction().commit();
              em.getTransaction().begin();
            //Delete existing users and roles to get a "fresh" database
            em.createQuery("delete from Person").executeUpdate();
            em.createQuery("delete from Address").executeUpdate();
            em.createQuery("delete from PhoneNumber").executeUpdate();
            em.createQuery("delete from Book").executeUpdate();
           
            em.persist(p1);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

     @Test
    public void testPerson() throws Exception {  
            
        given()
                .contentType("application/json")
                .accept(ContentType.JSON)
                .when()                
                .get("/Person/getAll").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("firstName", contains(p1.getFirstName()));
    }


    @Test
    public void deletePerson() throws Exception{
    
     given()
                .contentType("application/json")
                .accept(ContentType.JSON)
                .when()
                .delete("/Person/delete/" + p1.getId()).then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode());
    }
    @Disabled
    @Test
    public void createPerson() throws Exception{
      
   
        
        given()
                .contentType("application/json")
                .accept(ContentType.JSON)
                .when()
                .body(payload)
                .post("/Person/add/").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .extract()
                .response();
    
    }
    

}
