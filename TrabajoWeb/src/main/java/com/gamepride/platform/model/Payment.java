package com.gamepride.platform.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="payments")
public class Payment{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "Debe ingresar una fecha.")
	@Column(name="paid_at",nullable=false,length=50)
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss a")
	private  Date paidAt;
	
	@NotNull(message = "Debe elegir el tipo de pago.")
	@Column(name="type_pay",nullable=false)
	private String typePay;
	
	@NotNull(message="Debe seleccionar un plan de suscripción")
	@ManyToOne
	@JoinColumn(name="subscription_id",nullable=false)
	private Subscription subscriptionId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getPaidAt() {
		return paidAt;
	}

	public void setPaidAt(Date paidAt) {
		this.paidAt = paidAt;
	}

	public String getTypePay() {
		return typePay;
	}

	public void setTypePay(String typePay) {
		this.typePay = typePay;
	}

	public Subscription getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(Subscription subscriptionId) {
		this.subscriptionId = subscriptionId;
	}
}