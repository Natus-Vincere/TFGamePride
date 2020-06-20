package com.gamepride.platform.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.gamepride.platform.model.Event;

public interface IEventService extends ICrudService<Event>{

	void publishedEvent(Long id) throws Exception;
	
	void createdEvent(Long id) throws Exception;
	
	List<Event> findByGame(String game)throws Exception;
	
	Optional<Event> fetchByEventIdWithGamers(Long id)throws Exception;
	
	Collection<Event> getEvents()throws Exception;
}
