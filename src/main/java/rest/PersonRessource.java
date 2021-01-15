/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.PersonDTO;
import entities.Person;
import errorhandling.ErrorRetrieving;
import facades.FacadeExample;
import facades.PersonFacade;
import facades.FetchFacade;

import facades.RemoteServerFacade;
import java.io.IOException;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;

/**
 *
 * @author Benjamin
 */
@Path("Person")
public class PersonRessource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final FacadeExample FACADE =  FacadeExample.getFacadeExample(EMF);
   private static final PersonFacade personFacade =  PersonFacade.getPersonFacade(EMF);
 

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getAll")
    public String getAllPersons() throws ErrorRetrieving {
      
      return GSON.toJson( personFacade.getUsers());    
      
      
    }

    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("abc")
    public String fetch() throws IOException {
        FetchFacade ff = new FetchFacade();
        return GSON.toJson(ff.fetchData());    
      
      
    }
    
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getByID")
    public String getUserByID() throws ErrorRetrieving {
           return null;
    }
    
    @POST
    @Path("add")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String addPerson(String person) {
       
        PersonDTO p = GSON.fromJson(person, PersonDTO.class);
       personFacade.createUser(new Person(p.getFirstName(),p.getLastName(),p.getBirthyear()));
        return (p.getFirstName() + " " + p.getLastName() + " Has Been Created"  );
}


     @PUT
    @Path("update/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public void updatePerson(@PathParam("id") Long id, String person){
    
}
   
    @DELETE
    @Path("delete/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String deletePerson(@PathParam("id") Long id) {
        PersonDTO p = personFacade.deleteUserByID(id);
        return GSON.toJson(p);
}    
}
   
