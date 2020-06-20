package com.gamepride.platform.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

@Entity
@Table(name="events")
public class Event{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Debe ingresar un nombre de evento.")
	@Column(name="name",nullable=false,length=40)
	private String name;
	
	@NotEmpty(message = "Debe ingresar el nombre de un juego.")
	@Column(name="game",nullable=false,length=50)
	private String game;
	
	@Future(message = "El torneo no puede ser hoy, ingrese otra fecha.")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "Debe ingresar una fecha.")
	@Column(name="started_at",nullable=false)
	private Date startedAt;
	
	@NotEmpty(message = "Debe ingresar el número de vacantes.")
	@Column(name="vacancy",nullable=false)
	private String vacancy;
	
	@DecimalMin("0.00")
	@NumberFormat(style =Style.CURRENCY)
	@Column(name="cost_inscription",nullable=false,columnDefinition = "Decimal(8,2)")
	private Double costInscription;
	
	@NotEmpty(message = "Debe ingresar la recompensa del torneo.")
	@Column(name="reward",nullable=false,length=40)
	private String reward;

	@Column(name = "status", length = 50)
	private String status;
	
	@NotEmpty(message = "Debe ingresar las bases del torneo.")
	@Column(name="bases",nullable=false,length=255)
	private String bases;
	
	@NotEmpty(message = "Debe ingresar una foto referencial del torneo.")
	@Column(name="photo",nullable=false,length=200)
	private String photo;
	
	@ManyToMany(mappedBy = "events",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Gamer> gamers;
	
	@NotNull(message="Debe seleccionar un LanCenter")
	@ManyToOne
	@JoinColumn(name="lancenter_id",nullable=false)
	private LanCenter lancenterId;
	
	public Event() {
		gamers=new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

	public Date getStartedAt() {
		return startedAt;
	}

	public void setStartedAt(Date startedAt) {
		this.startedAt = startedAt;
	}

	public String getVacancy() {
		return vacancy;
	}

	public void setVacancy(String vacancy) {
		this.vacancy = vacancy;
	}

	public Double getCostInscription() {
		return costInscription;
	}

	public void setCostInscription(Double costInscription) {
		this.costInscription = costInscription;
	}

	public String getReward() {
		return reward;
	}

	public void setReward(String reward) {
		this.reward = reward;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBases() {
		return bases;
	}

	public void setBases(String bases) {
		this.bases = bases;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public List<Gamer> getGamers() {
		return gamers;
	}

	public void setGamers(List<Gamer> gamers) {
		this.gamers = gamers;
	}

	public LanCenter getLancenterId() {
		return lancenterId;
	}

	public void setLancenterId(LanCenter lancenterId) {
		this.lancenterId = lancenterId;
	}
}