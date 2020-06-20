package com.gamepride.platform.service;

import java.util.Optional;

public interface ICrudService<T> {
	
	public int create(T t) throws Exception;
	
	public Optional<T> findById(Long id) throws Exception;
	
	public void deleteById(Long id) throws Exception;
}
