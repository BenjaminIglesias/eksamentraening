/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import dto.FetchDTO;
import dto.filmDTO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import utils.HttpUtils;
import java.lang.reflect.Type;


/**
 *
 * @author Benjamin
 */
public class FetchFacade {
   
      private static EntityManagerFactory emf;
      private static FetchFacade instance;
      private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    
   
    public FetchFacade(){}
      /**
     *
     * @param _emf
     * @return the instance of this facade.
     */
    public static FetchFacade getFetchFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new FetchFacade();
        }
        return instance;
    }
    
    
    public List<FetchDTO> fetchData() throws IOException{
    String FetchDataJson = HttpUtils.fetchData("https://jsonplaceholder.typicode.com/todos/");    
        
    Type fetchListType = new TypeToken<ArrayList<FetchDTO>>(){}.getType();    
    ArrayList<FetchDTO> fetched = GSON.fromJson(FetchDataJson, fetchListType);
   
    fetched.forEach(fetchDto -> {
        System.out.println(fetchDto);
          });
    
    return fetched;
    }
 
    public FetchDTO fetchSingleData() throws IOException{
    
     String fetched = HttpUtils.fetchData("https://jsonplaceholder.typicode.com/todos/1");
        System.out.println(fetched);
        //FetchDTO fetchdto = GSON.fromJson(fetched, FetchDTO.class);
    
        return null;
    }
}
