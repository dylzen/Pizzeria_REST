package com.advancia.stage.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.util.List;


/**
 * The persistent class for the impasto database table.
 * 
 */
@Entity
@XmlRootElement
@NamedQuery(name="Impasto.findAll", query="SELECT i FROM Impasto i")
@ApiModel(description = "Un impasto che potrebbe essere disponibile per le pizze.")
public class Impasto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_impasto")
	@ApiModelProperty(value = "Identificatore univoco")
	private int idImpasto;

	@Column(name="nome_impasto")
	private String nomeImpasto;

	//bi-directional many-to-one association to Pizza
    @JsonIgnore
	@OneToMany(mappedBy="impasto")
	private List<Pizza> pizzas;

	public Impasto() {
	}

	public int getIdImpasto() {
		return this.idImpasto;
	}

	public void setIdImpasto(int idImpasto) {
		this.idImpasto = idImpasto;
	}

	public String getNomeImpasto() {
		return this.nomeImpasto;
	}

	public void setNomeImpasto(String nomeImpasto) {
		this.nomeImpasto = nomeImpasto;
	}

	public List<Pizza> getPizzas() {
		return this.pizzas;
	}

	public void setPizzas(List<Pizza> pizzas) {
		this.pizzas = pizzas;
	}

	public Pizza addPizza(Pizza pizza) {
		getPizzas().add(pizza);
		pizza.setImpasto(this);

		return pizza;
	}

	public Pizza removePizza(Pizza pizza) {
		getPizzas().remove(pizza);
		pizza.setImpasto(null);

		return pizza;
	}

}