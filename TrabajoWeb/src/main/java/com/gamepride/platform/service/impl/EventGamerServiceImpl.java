package com.gamepride.platform.service.impl;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.gamepride.platform.model.EventGamer;

import com.gamepride.platform.repository.IEventGamerRepository;

import com.gamepride.platform.service.IEventGamerService;

public class EventGamerServiceImpl implements IEventGamerService{

	@Autowired
	private IEventGamerRepository eventgamerRepository;
	
	@Transactional
	@Override
	public int create(EventGamer eg) throws Exception {
		int result=eventgamerRepository.countByInscriptedAt(eg.getInscriptedAt());
		if(result==0)
		{
			eventgamerRepository.save(eg);
		}
		return result;
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<EventGamer> findById(Long id) throws Exception {
		return eventgamerRepository.findById(id);
	}

	@Transactional
	@Override
	public void deleteById(Long id) throws Exception {
		eventgamerRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public Collection<EventGamer> getEventGamers() throws Exception {
		return eventgamerRepository.findAllByOrderByInscriptedAtDesc();
	}

}
