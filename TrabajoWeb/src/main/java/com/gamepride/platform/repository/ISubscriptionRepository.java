package com.gamepride.platform.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.gamepride.platform.model.Subscription;

@Repository
public interface ISubscriptionRepository extends JpaRepository<Subscription, Long>{

	@Query("select count(s.type) from Subscription s where s.type = :type")
	int countByType(@Param("type") String type);
	
	Collection<Subscription> findAllByOrderByTypeDesc();
}
