package com.gamepride.platform.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gamepride.platform.model.Event;

@Repository
public interface IEventRepository extends JpaRepository<Event, Long> {
	
	@Modifying
	@Query(value = "UPDATE events set status='Publicado' where id =?1", nativeQuery = true)
	void publishedEvent(Long id);
	
	@Modifying
	@Query(value = "UPDATE events set status='Creado' where id =?1", nativeQuery = true)
	void createdEvent(Long id);
	
	List<Event> findByGame(String game);
	
	@Query("select e from Event e left join fetch e.gamers g where e.id=?1")
	Optional<Event> fetchByEventIdWithGamers(Long id);
	
	@Query("select count(e.name) from Event e where e.name = :name")
	int countByName(@Param("name") String name);
	
	Collection<Event> findAllByOrderByNameDesc();
}