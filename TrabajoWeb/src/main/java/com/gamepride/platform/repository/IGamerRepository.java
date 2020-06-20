package com.gamepride.platform.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gamepride.platform.model.Gamer;

@Repository
public interface IGamerRepository extends JpaRepository<Gamer, Long> {

	List<Gamer> findByUsername(String username);
	
	@Query("select g from Gamer g left join fetch g.events e where g.id=?1")
	Optional<Gamer> fetchByGamerIdWithEvents(Long id);
	
	@Query("select count(g.username) from Gamer g where g.username = :username")
	int countByUsername(@Param("username") String username);
	
	Collection<Gamer> findAllByOrderByUsernameDesc();
}