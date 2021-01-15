/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import errorhandling.ErrorRetrieving;
import facades.PersonFacade;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import rest.PersonRessource;

/**
 *
 * @author Benjamin
 */
public class TestPlaceMain {
    public static void main(String[] args) throws ErrorRetrieving {
         EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
    EntityManager em = emf.createEntityManager();
      PersonFacade pf =  PersonFacade.getPersonFacade(emf);
 
        PersonRessource pr = new  PersonRessource(); 
        pr.fillDB();
        
    }}
   
     
  