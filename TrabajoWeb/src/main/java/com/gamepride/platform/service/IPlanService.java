package com.gamepride.platform.service;


import java.util.Collection;

import com.gamepride.platform.model.Plan;

public interface IPlanService extends ICrudService<Plan> {
	
	Collection<Plan> getPlans() throws Exception;
}