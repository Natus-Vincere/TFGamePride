package com.gamepride.platform.service.impl;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gamepride.platform.model.Payment;
import com.gamepride.platform.repository.IPaymentRepository;
import com.gamepride.platform.service.IPaymentService;

@Service
public class PaymentServiceImpl implements IPaymentService {

	@Autowired
	private IPaymentRepository paymentRepository;
	

	@Transactional
	@Override
	public int create(Payment p) throws Exception {
		int result=paymentRepository.countByTypePay(p.getTypePay());
		if(result==0)
		{
			paymentRepository.save(p);
		}
		return result;
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Payment> findById(Long id) throws Exception {
		return paymentRepository.findById(id);
	}

	@Transactional
	@Override
	public void deleteById(Long id) throws Exception {
		paymentRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public Collection<Payment> getPayments() throws Exception {
		return paymentRepository.findAllByOrderByTypePayDesc();
	}

}