package com.gamepride.platform.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gamepride.platform.model.Gamer;
import com.gamepride.platform.repository.IGamerRepository;
import com.gamepride.platform.service.IGamerService;

@Service
public class GamerServiceImpl implements IGamerService {

	@Autowired
	private IGamerRepository gamerRepository;

	@Transactional
	@Override
	public int create(Gamer g) throws Exception {
		int result=gamerRepository.countByUsername(g.getUsername());
		if(result==0)
		{
			gamerRepository.save(g);
		}
		return result;
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Gamer> findById(Long id) throws Exception {
		return gamerRepository.findById(id);
	}

	@Transactional
	@Override
	public void deleteById(Long id) throws Exception {
		gamerRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Gamer> findByUsername(String username) throws Exception {
		return gamerRepository.findByUsername(username);
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Gamer> fetchByGamerIdWithEvents(Long id) throws Exception {
		return gamerRepository.fetchByGamerIdWithEvents(id);
	}

	@Transactional(readOnly = true)
	@Override
	public Collection<Gamer> getGamers() throws Exception {
		return gamerRepository.findAllByOrderByUsernameDesc();
	}
}