package com.gamepride.platform.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.gamepride.platform.model.LanCenter;

public interface ILanCenterService extends ICrudService<LanCenter>{

	List<LanCenter> findByName(String name) throws Exception;
	
	Optional<LanCenter> fetchByLanCenterIdWithEvents(Long id) throws Exception;
	
	Optional<LanCenter> fetchByLanCenterIdWithEventsWithGamers(Long id) throws Exception;
	
	Collection<LanCenter> getLanCenters()throws Exception;
}