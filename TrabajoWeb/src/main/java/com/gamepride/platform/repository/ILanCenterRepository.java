package com.gamepride.platform.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gamepride.platform.model.LanCenter;

@Repository
public interface ILanCenterRepository extends JpaRepository<LanCenter, Long> {
	
	@Query("SELECT l FROM LanCenter l left join fetch l.events WHERE l.id=?1")
	Optional<LanCenter> fetchByLanCenterIdWithEvents(Long id);

	List<LanCenter> findByName(String name);
	 
	@Query("select l from LanCenter l join fetch l.events e join fetch l.gamerId p where l.id=?1")
	Optional<LanCenter> fetchByLanCenterIdWithEventsWithGamers(Long id);
	
	@Query("select count(l.name) from LanCenter l where l.name = :name")
	int countByName(@Param("name") String name);
	
	Collection<LanCenter> findAllByOrderByNameDesc();
}
