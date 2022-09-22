package com.advancia.stage.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import com.advancia.stage.dao.PizzaDAO;
import com.advancia.stage.model.Pizza;
import com.advancia.stage.model.Utente;
import com.advancia.stage.utility.Converter;
import com.wordnik.swagger.annotations.*;

@Path("/swagger")
@Api(value="/swagger", description = "Operations about user")
@Produces({"application/json", "application/xml"})
public class UserResource {
    @GET
    @Path("/utente/{idUtente}")
    @ApiOperation(
        value = "Get user by user name",
        response = Utente.class,
        position = 0)
    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "Invalid username supplied"),
        @ApiResponse(code = 404, message = "User not found")})
    public List<Pizza> getUserByName(
        @ApiParam(
            value = "The name that needs to be fetched. Use user1 for testing. ", 
            required = true)
        @PathParam("idUtente") String idUtente)
     {
    	List<Pizza> listaPizze = PizzaDAO.findPizzaByIdUtente(idUtente);
    	return Converter.cleanPizza(listaPizze);
    }
}
