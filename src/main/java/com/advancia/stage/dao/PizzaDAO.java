package com.advancia.stage.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.advancia.stage.dto.PizzaDTO;
import com.advancia.stage.model.Impasto;
import com.advancia.stage.model.Ingrediente;
import com.advancia.stage.model.Pizza;
import com.advancia.stage.model.Utente;



public class PizzaDAO {
	
	static EntityManagerFactory emf = null;
	static EntityManager entityManager = null;
	
	private static void gestisciEntityManager() {
		if (emf == null || entityManager == null) {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("Pizzeria");
			entityManager = emf.createEntityManager();
		}
	}
//	private void chiudiTransazione() {
//		entityManager.getTransaction().commit();
//        entityManager.close();
//    }

	public static void createPizzaRecord(Pizza pizza) {
		gestisciEntityManager();
		entityManager.getTransaction().begin();

		Pizza newPizza = new Pizza();

		newPizza.setNomePizza(pizza.getNomePizza()); // deve venire da text field
		newPizza.setImpasto(pizza.getImpasto()); // deve venire da radio button
		newPizza.setIngredientes(pizza.getIngredientes()); // venire da checkbox
		newPizza.setUtente(pizza.getUtente());

		entityManager.persist(newPizza);

		entityManager.getTransaction().commit();
	}

	public static List<Pizza> findAllPizza() {
		gestisciEntityManager();
		entityManager.getTransaction().begin();
		
		List<Pizza> foundPizzas = entityManager.createQuery("from Pizza", Pizza.class).getResultList();
		entityManager.getTransaction().commit();
		return foundPizzas;
		
	}
	
	public static Pizza findPizzaByIdPizza(int idPizza) {
		gestisciEntityManager();
		entityManager.getTransaction().begin();
		
		Pizza retrievedPizza = entityManager.find(Pizza.class, idPizza);
		System.out.println("Ora sono nella classe PizzaDAO, funzione findPizzaByIdPizza");

		entityManager.getTransaction().commit();
		return retrievedPizza;
	}
	
	public static List<Pizza> findPizzaByIdUtente(String idUtente) {
		System.out.println("Sono entrato in findPizzaByIdUtente");
		gestisciEntityManager();
		entityManager.getTransaction().begin();

		@SuppressWarnings("unchecked")
		List<Pizza> retrievedPizze = entityManager.createQuery("select p from Pizza p where p.utente.idUtente = ?")
				.setParameter(1, Integer.valueOf(idUtente)).getResultList();
		System.out.println("Ora sono nella classe PizzaDAO, funzione findPizzaByIdUtente");
//		for (Pizza pizza : retrievedPizze) {
//			System.out.println(pizza.getImpasto().getNomeImpasto());
//			
//		}
		System.out.println("Pizze dell'utente trovate, ritorno");
		entityManager.getTransaction().commit();
		return retrievedPizze;

	}

	public List<Impasto> findAllImpasti() {
		gestisciEntityManager();
		entityManager.getTransaction().begin();
		List<Impasto> foundImpasti = entityManager.createQuery("from Impasto", Impasto.class).getResultList();
		
		entityManager.getTransaction().commit();
		return foundImpasti;		
	}

	public List<Ingrediente> findAllIngredienti() {
		gestisciEntityManager();
		entityManager.getTransaction().begin();
		List<Ingrediente> foundIngredientes = entityManager.createQuery("from Ingrediente", Ingrediente.class).getResultList();
		
		entityManager.getTransaction().commit();
		return foundIngredientes;
		
	}

	public List<Utente> findAllUtenti() {
		gestisciEntityManager();
		entityManager.getTransaction().begin();
		
		List<Utente> foundUtenti = entityManager.createQuery("from Utente", Utente.class).getResultList();
		
		entityManager.getTransaction().commit();
		return foundUtenti;
		
	}
	
	public static void deletePizzaByID(int idPizza) {

		gestisciEntityManager();
		entityManager.getTransaction().begin();
	
		Pizza pizzaDaEliminare = entityManager.find(Pizza.class, idPizza);
		entityManager.remove(pizzaDaEliminare); // cancella la pizza dal DB
		System.out.println("Sono in PizzaDAO, ho appena rimosso la pizza con l'entityManager");
		entityManager.getTransaction().commit();

	}
	
	public static void updatePizzaByID(Pizza pizza) {
		gestisciEntityManager();
		entityManager.getTransaction().begin();
		
		Pizza pizzaDaModificare = entityManager.find(Pizza.class, pizza.getIdPizza());
		System.out.println("################ TEST UPDATE PIZZA ##################");
		System.out.println(pizzaDaModificare.getNomePizza() + " - " + pizzaDaModificare.getImpasto().getNomeImpasto());
		
		
		pizzaDaModificare.setNomePizza(pizza.getNomePizza());
		pizzaDaModificare.setImpasto(pizza.getImpasto());
		pizzaDaModificare.setIngredientes(pizza.getIngredientes());
	
		entityManager.merge(pizzaDaModificare);
		entityManager.getTransaction().commit();

	}
	
	public static List<PizzaDTO> getAllPizzaDTO() {
		List<PizzaDTO> listaPizzaDTO = new ArrayList<>();
		gestisciEntityManager();
		entityManager.getTransaction().begin();
		TypedQuery<PizzaDTO> query = entityManager.createQuery("select new com.advancia.stage.dto.PizzaDTO(p.id, p.nomePizza) from Pizza p", PizzaDTO.class); // lancio costruttore di PizzaDTO
		listaPizzaDTO = query.getResultList();
		
		return listaPizzaDTO;
	}

}