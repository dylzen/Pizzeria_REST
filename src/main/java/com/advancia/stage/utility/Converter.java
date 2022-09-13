package com.advancia.stage.utility;

import java.util.List;
// import java.util.stream.Collectors;

import com.advancia.stage.model.Pizza;

public class Converter {

	public static List<Pizza> cleanPizza(List<Pizza> listaPizze) {
		
		listaPizze.forEach(pizza -> {
			pizza.getUtente().setPizzas(null);
			pizza.getImpasto().setPizzas(null);
			pizza.getIngredientes().forEach(ingrediente -> ingrediente.setPizzas(null));
		});
		// oppure
//		listaPizze = listaPizze.stream().filter(pizza -> pizza.getIdPizza() > 0).collect(Collectors.toList());
		
		return listaPizze;
	}
	

}
