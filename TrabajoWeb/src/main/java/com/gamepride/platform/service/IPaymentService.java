package com.gamepride.platform.service;

import java.util.Collection;

import com.gamepride.platform.model.Payment;

public interface IPaymentService extends ICrudService<Payment>{
	
	Collection<Payment> getPayments() throws Exception;
}
