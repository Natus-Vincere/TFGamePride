package com.gamepride.platform.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="lancenters")
public class LanCenter{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Debe ingresar el nombre del LanCenter.")
	@Column(name="name",nullable=false,length=50)
	private String name;
	
	@Pattern(regexp = "[123456789][0-9]{6}",message = "El número de teléfono solo debe contener números y no puebe empezar con el número 0.")
	@NotEmpty(message = "Debe ingresar el número del LanCenter.")
	@Size(min = 7,max = 7,message="Número de teléfono inválido.")
	@Column(name="phone",nullable=false)
	private String phone;
	
	@NotEmpty(message = "Debe ingresar la dirección del LanCenter.")
	@Column(name="address",nullable=false,length=60)
	private String address;
	
	@NotEmpty(message = "Debe ingresar el distrito del LanCenter.")
	@Column(name="district",nullable=false,length=60)
	private String district;

	@OneToOne
	@JoinColumn(name="gamer_id",nullable=false)
	private Gamer gamerId;

	@OneToMany(mappedBy = "lancenterId",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Event> events;
	
	public LanCenter() {
		events=new ArrayList<>();
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAdress(String address) {
		this.address = address;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public Gamer getGamerId() {
		return gamerId;
	}

	public void setGamerId(Gamer gamerId) {
		this.gamerId = gamerId;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}
}