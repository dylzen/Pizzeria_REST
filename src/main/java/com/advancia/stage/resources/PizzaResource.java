package com.advancia.stage.resources;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.advancia.stage.dao.PizzaDAO;
import com.advancia.stage.dto.PizzaDTO;
import com.advancia.stage.model.Pizza;
import com.advancia.stage.utility.Converter;

@Path("/pizzas")
public class PizzaResource {

	@GET
	@Path("/hello")
	@Produces({ MediaType.APPLICATION_JSON })
	public String sayJsonHello() {
		return "{\"name\":\"greeting\", \"message\":\"Bonjour tout le monde!\"}";
	}

	// URI:
	// http://localhost:8080/pizzeriadadylan/rest/pizzas/
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Pizza> getPizzas_JSON() {
		List<Pizza> listaPizze = PizzaDAO.findAllPizza();
		return Converter.cleanPizza(listaPizze);
	}

	// URI:
	// /contextPath/servletPath/pizzas/{idPizza}
	@GET
	@Path("/pizza/{idPizza}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Pizza getPizzaByPizzaID_JSON(@PathParam("idPizza") int idPizza) {
		Pizza pizza = PizzaDAO.findPizzaByIdPizza(idPizza);
		return pizza;
	}

	// URI:
	// /contextPath/servletPath/pizzas/utente/{idUtente}
	@GET
	@Path("/utente/{idUtente}")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Pizza> getPizzaByUserID_JSON(@PathParam("idUtente") String idUtente) {
		List<Pizza> listaPizze = PizzaDAO.findPizzaByIdUtente(idUtente);
		return Converter.cleanPizza(listaPizze);
	}
	
	 // URI:
    // /contextPath/servletPath/pizzas
    @POST
    @Path("/aggiungi")
    @Produces({ MediaType.APPLICATION_JSON })
    public void addPizza(Pizza pizza) {
        PizzaDAO.createPizzaRecord(pizza);
    }
    
    @DELETE
    @Path("/elimina/{idPizza}")
    @Produces({ MediaType.APPLICATION_JSON })
    public void deletePizza(@PathParam("idPizza") int idPizza) {
        PizzaDAO.deletePizzaByID(idPizza);
    }
    
    @PUT
    @Path("/aggiorna")
    @Produces({ MediaType.APPLICATION_JSON })
    public void updateEmployee(Pizza pizza) {
        PizzaDAO.updatePizzaByID(pizza);
    }
	
	@GET
	@Path("/dto")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<PizzaDTO> getPizzas_DTO() {
		List<PizzaDTO> listaPizze = PizzaDAO.getAllPizzaDTO();
		return listaPizze;
	}

}
