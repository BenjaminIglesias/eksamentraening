/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package errorhandling;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import security.errorhandling.AuthenticationException;

/**
 *
 * @author Benjamin
 */
    @Provider
public class ErrorRetrievingMapper implements ExceptionMapper<ErrorRetrieving> {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final int ERROR_CODE = 500;
    @Context
    ServletContext context;

    

    @Override
    public Response toResponse(ErrorRetrieving ex) {
        ExceptionDTO err = new ExceptionDTO(ERROR_CODE, ex.getMessage());
        return Response.status(ERROR_CODE).entity(gson.toJson(err)).type(MediaType.APPLICATION_JSON).build();
    }

}

