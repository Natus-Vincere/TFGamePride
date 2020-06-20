package com.gamepride.platform.service.impl;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gamepride.platform.model.Plan;
import com.gamepride.platform.repository.IPlanRepository;
import com.gamepride.platform.service.IPlanService;

@Service
public class PlanServiceImpl implements IPlanService {

	@Autowired
	private IPlanRepository planRepository;

	@Transactional
	@Override
	public int create(Plan p) throws Exception {
		int result=planRepository.countByType(p.getType());
		if(result==0)
		{
			planRepository.save(p);
		}
		return result;
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Plan> findById(Long id) throws Exception {
		return planRepository.findById(id);
	}

	@Transactional
	@Override
	public void deleteById(Long id) throws Exception {
		planRepository.deleteById(id);
	}


	@Transactional(readOnly = true)
	@Override
	public Collection<Plan> getPlans() throws Exception {
		return planRepository.findAllByOrderByTypeDesc();
	}
}
