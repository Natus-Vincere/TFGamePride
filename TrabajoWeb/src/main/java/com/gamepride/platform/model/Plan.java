package com.gamepride.platform.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="plans")
public class Plan{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Debe elegir el tipo de plan.")
	@Column(name="type",nullable=false,length=20)
	private String type;
	
	@DecimalMin("0.00")
	@DecimalMax("30.00")
	@Column(name="cost",nullable=false,columnDefinition = "Decimal(8,2)")
	private Double cost;
	
	@ManyToMany(mappedBy = "plans")
	private List<Gamer> gamers;
	
	public Plan() {
		gamers=new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public List<Gamer> getGamers() {
		return gamers;
	}

	public void setGamers(List<Gamer> gamers) {
		this.gamers = gamers;
	}
}