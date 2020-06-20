package com.gamepride.platform.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gamepride.platform.model.Plan;

@Repository
public interface IPlanRepository extends JpaRepository<Plan, Long> {

	@Query("select count(p.type) from Plan p where p.type = :type")
	int countByType(@Param("type") String type);
	
	Collection<Plan> findAllByOrderByTypeDesc();
}