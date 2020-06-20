package com.gamepride.platform.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gamepride.platform.model.Subscription;
import com.gamepride.platform.repository.ISubscriptionRepository;
import com.gamepride.platform.service.ISubscriptionService;

@Service
public class SubscriptionServiceImpl implements ISubscriptionService {

	@Autowired
	private ISubscriptionRepository subscriptionRepository;

	@Transactional(readOnly = true)
	@Override
	public Collection<Subscription> getSubscription() throws Exception {
		return subscriptionRepository.findAllByOrderByTypeDesc();
	}
}

