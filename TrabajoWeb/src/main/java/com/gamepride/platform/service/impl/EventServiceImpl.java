package com.gamepride.platform.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gamepride.platform.model.Event;
import com.gamepride.platform.repository.IEventRepository;
import com.gamepride.platform.service.IEventService;

@Service
public class EventServiceImpl implements IEventService {

	@Autowired
	private IEventRepository eventRepository;

	@Transactional
	@Override
	public int create(Event e) throws Exception {
		int result=eventRepository.countByName(e.getName());
		if(result==0)
		{
			e.setStatus("Creado");
			eventRepository.save(e);
		}
		return result;
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Event> findById(Long id) throws Exception {
		return eventRepository.findById(id);
	}

	@Transactional
	@Override
	public void deleteById(Long id) throws Exception {
		eventRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public void publishedEvent(Long id) throws Exception {
		eventRepository.publishedEvent(id);
	}

	@Transactional(readOnly = true)
	@Override
	public void createdEvent(Long id) throws Exception {
		eventRepository.createdEvent(id);		
	}

	@Transactional(readOnly = true)
	@Override
	public List<Event> findByGame(String game) throws Exception {
		return eventRepository.findByGame(game);
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Event> fetchByEventIdWithGamers(Long id) throws Exception {
		return eventRepository.fetchByEventIdWithGamers(id);
	}

	@Transactional(readOnly = true)
	@Override
	public Collection<Event> getEvents() throws Exception {
		return eventRepository.findAllByOrderByNameDesc();
	}
}