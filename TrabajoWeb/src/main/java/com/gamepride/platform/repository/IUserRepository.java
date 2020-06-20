package com.gamepride.platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gamepride.platform.model.Gamer;

@Repository
public interface IUserRepository extends JpaRepository<Gamer,Long>{
	public Gamer findByUsername(String username);
}
