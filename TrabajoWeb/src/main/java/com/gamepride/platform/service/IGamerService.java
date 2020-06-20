package com.gamepride.platform.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.gamepride.platform.model.Gamer;

public interface IGamerService extends ICrudService<Gamer> {

	List<Gamer> findByUsername(String username) throws Exception; 
	
	Optional<Gamer> fetchByGamerIdWithEvents(Long id) throws Exception;
	
	Collection<Gamer> getGamers()throws Exception;
}
