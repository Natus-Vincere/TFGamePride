package com.gamepride.platform.service;

import java.util.Collection;

import com.gamepride.platform.model.Subscription;

public interface ISubscriptionService{
	
	Collection<Subscription> getSubscription() throws Exception;
}
