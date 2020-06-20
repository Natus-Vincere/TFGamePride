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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="subscriptions")
public class Subscription{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "Debe elegir el tipo de suscripción.")
	@Column(name="type",nullable=false,length=20)
	private String type;

	@Column(name="subscripted_at",nullable=false)
	@Temporal(TemporalType.DATE)
	private Date subscriptedAt;
	
	
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="subscription_id")
	private List<Payment>payments;
	
	public Subscription() {
		payments=new ArrayList<>();
	}
	
	@PrePersist
	public void prePersist()
	{
		subscriptedAt=new Date();
	}


	public Date getSubscriptedAt() {
		return subscriptedAt;
	}


	public void setSubscriptedAt(Date subscriptedAt) {
		this.subscriptedAt = subscriptedAt;
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

}