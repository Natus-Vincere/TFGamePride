package com.gamepride.platform.service;

import java.util.Collection;

import com.gamepride.platform.model.EventGamer;



public interface IEventGamerService extends ICrudService<EventGamer> {
	Collection<EventGamer> getEventGamers() throws Exception;
}
